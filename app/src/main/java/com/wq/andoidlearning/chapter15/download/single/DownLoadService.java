package com.wq.andoidlearning.chapter15.download.single;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wq.andoidlearning.chapter15.download.single.entities.FileInfo;
import com.wq.andoidlearning.chapter15.download.single.service.DownLoadTask;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadService extends Service {
    public static final String TAG = "DownLoadService";
    //开始下载
    public static final String ACTION_START = "ACTION_START";
    //暂停下载
    public static final String ACTION_STOP = "ACTION_STOP";
    //更新进度
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/downloads";
    public static final int MSG_INIT = 0;
    private DownLoadTask downLoadTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        //获取activity传递过来的参数
        if (ACTION_START.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
            Log.e(TAG, ACTION_START);
            //开启线程
            InitThread thread = new InitThread(fileInfo);
            thread.start();
        } else if (ACTION_STOP.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("file");
            Log.e(TAG, ACTION_STOP);
            if (downLoadTask != null) {
                //暂停下载
                downLoadTask.isPause = true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_INIT:
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    Log.e(TAG, MSG_INIT + "");
                    //启动下载任务
                    downLoadTask = new DownLoadTask(DownLoadService.this, fileInfo);
                    downLoadTask.downLoad();
                    break;
            }
        }
    };

    class InitThread extends Thread {
        private FileInfo fileInfo;

        public InitThread(FileInfo fileInfo) {
            this.fileInfo = fileInfo;
        }

        @Override
        public void run() {
            super.run();
            HttpURLConnection connection = null;
            RandomAccessFile randomAccessFile = null;
            int length = -1;
            try {
                //连接网络文件
                URL url = new URL(fileInfo.url);
                connection = (HttpURLConnection) url.openConnection();
                //设置链接超时
                connection.setConnectTimeout(6000);
                //设置请求方式
                connection.setRequestMethod("GET");
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //获得文件的大小
                    length = connection.getContentLength();
                }
                if (length < 0) {
                    return;
                }
                Log.e(TAG, length + "");
                File dir = new File(DOWNLOAD_PATH);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                //在本地创建文件
                File file = new File(dir, fileInfo.fileName);
                randomAccessFile = new RandomAccessFile(file, "rwd");
                //设置文件的长度
                randomAccessFile.setLength(length);
                fileInfo.length = length;
                //通过Handler发送信息进行文件的下载
                Message message = handler.obtainMessage(MSG_INIT, fileInfo);
                handler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
