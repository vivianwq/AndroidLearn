package com.wq.andoidlearning.doodle;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.wq.andoidlearning.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageLoader {
    public static final String TAG = "ImageLoader";
    public static final int MESSAGE_POST_RESULT = 1;
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    public static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    public static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    public static final long KEEP_ALIVE = 10L;


    public static final int TAG_KEY_URI = R.id.imageloader_uri;
    public static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;
    public static final int IO_BUFFER_SIZE = 8 * 1024;
    public static final int DISK_CACHE_INDEX = 0;
    private boolean isDiskLruCacheCreated = false;


    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger count = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ImageLoader#" + count.getAndIncrement());
        }
    };

    public static final Executor THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
            TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), sThreadFactory);

    private Handler mainHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            imageView.setImageBitmap(result.bitmap);
            String uri = (String) imageView.getTag(TAG_KEY_URI);
            if (uri.equals(result.uri)) {
                imageView.setImageBitmap(result.bitmap);
            } else {
                Log.w(TAG, "set image bitmap,but url has changed , ignored!");
            }
        }
    };


    private Context context;
    private ImageResize imageResize = new ImageResize();
    private LruCache<String, Bitmap> memoryCache;
    private DiskLruCache diskLruCache;

    public ImageLoader(Context context) {
        this.context = context.getApplicationContext();
        //分配内存缓存为当前进程的1/8,磁盘缓存容量为50M
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);

        int maxMemory = (int) memoryInfo.availMem;
        int cacheSize = maxMemory / 8;
        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
        File diskCacheDir = getDiskCacheDir(context, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }
        if (getUsableSpace(diskCacheDir) > DISK_CACHE_SIZE) {
            try {
                diskLruCache = DiskLruCache.open(diskCacheDir, 1, 1,
                        DISK_CACHE_SIZE);
                isDiskLruCacheCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ImageLoader imageLoader;

    public static ImageLoader build(Context context) {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(context);
        }
        return imageLoader;
    }

    //同步加载
    public Bitmap loadBitmap(String uri, int reqWidth, int reqHeight) {
        Bitmap bitmap = loadBitmapFromMemCache(uri);
        Log.e(TAG, "再次尝试从缓存中获取");
        if (bitmap != null) {
            Log.e(TAG, "缓存中有,直接返回展示");
            return bitmap;
        }
        try {
            Log.e(TAG, "尝试从内存中获取");
            bitmap = loadBitmapForDiskCache(uri, reqWidth, reqHeight);
            if (bitmap != null) {
                return bitmap;
            }
            Log.e(TAG, "需要从网络获取数据");
            bitmap = loadBitmapFromHttp(uri, reqWidth, reqHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bitmap == null && !isDiskLruCacheCreated) {
            Log.e(TAG, "开拓存储空间失败,直接从网络下载文件");
            bitmap = downloadBitmapFromUrl(uri);
        }
        return bitmap;
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap);
        }
    }

    public void bindBitmap(final String uri, final ImageView imageView) {
        bindBitmap(uri, imageView, 0, 0);
    }

    public void bindBitmap(final String uri, final ImageView imageView,
                           final int reqWidth, final int reqHeight) {
        imageView.setTag(TAG_KEY_URI, uri);
        Bitmap bitmap = loadBitmapFromMemCache(uri);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            Log.e(TAG, "缓存中已经有,直接展示");
            return;
        }
        Runnable loadBitmapTask = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(uri, reqWidth, reqHeight);
                if (bitmap != null) {
                    LoaderResult result = new LoaderResult(imageView, uri, bitmap);
                    mainHandler
                            .obtainMessage(MESSAGE_POST_RESULT, result)
                            .sendToTarget();
                }
            }
        };
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }

    private Bitmap loadBitmapFromMemCache(String url) {
        final String key = hashKeyFormUrl(url);
        Bitmap bitmap = getBitmapFromMemCache(key);
        return bitmap;
    }

    //将下载的图片写入文件系统,实现磁盘缓存
    private Bitmap loadBitmapFromHttp(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from UI Thread");
        }
        if (diskLruCache == null)
            return null;
        String key = hashKeyFormUrl(url);
        DiskLruCache.Editor editor = diskLruCache.edit(key);
        if (editor != null) {
            OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            if (downloadUrlToStream(url, outputStream)) {
                editor.commit();
            } else {
                editor.abort();
            }
        }
        diskLruCache.flush();
        return loadBitmapForDiskCache(url, reqWidth, reqHeight);
    }

    private Bitmap loadBitmapForDiskCache(String url, int reqWidth, int reqHeight)
            throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w(TAG, "load bitmap from UI Thread,it's not recommended");
        }
        if (diskLruCache == null)
            return null;
        Bitmap bitmap = null;
        String key = hashKeyFormUrl(url);
        DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
        if (snapshot != null) {
            FileInputStream fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);

            FileDescriptor fileDescriptor = fileInputStream.getFD();
            bitmap = imageResize.decodeSampledBitmapFromBitmapFileDescriptor(
                    fileDescriptor, reqWidth, reqHeight
            );
            Log.e(TAG, "从DiskLruCache中获取到数据--");
            if (bitmap != null) {
                Log.e(TAG, "将内存中的图片数据加载到缓存中");
                addBitmapToMemoryCache(key, bitmap);
            }
        }
        return bitmap;

    }


    public boolean downloadUrlToStream(String urlString,
                                       OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream inputStream = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(urlConnection.getInputStream(),
                    IO_BUFFER_SIZE);
            outputStream = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);
            int b;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "downloadBitmap failed ." + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            MyUtils.close(outputStream);
            MyUtils.close(inputStream);
        }
        return false;
    }

    private Bitmap downloadBitmapFromUrl(String urlString) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(),
                    IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error in downloadBitmap:" + e);
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            MyUtils.close(in);
        }
        return bitmap;
    }

    private String hashKeyFormUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(url.getBytes());
            cacheKey = bytesToHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        boolean externalStorageAvailable = Environment
                .getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    private long getUsableSpace(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        final StatFs statFs = new StatFs(path.getPath());
        return (long) statFs.getBlockSize() * (long) statFs.getAvailableBlocks();
    }

    private static class LoaderResult {
        public ImageView imageView;
        public String uri;
        public Bitmap bitmap;

        public LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
            this.imageView = imageView;
            this.uri = uri;
            this.bitmap = bitmap;
        }
    }

}
