package com.wq.andoidlearning.bitmap;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class BitmapConvertUtils {

    public static Bitmap fromResourceIdAutoScale(Resources resources, int resourceId,
                                                 BitmapFactory.Options options) {
        return BitmapFactory.decodeResource(resources, resourceId, options);
    }

    public static Bitmap fromResourceIdNotScale(Resources resources, int resourceId,
                                                Rect rect,
                                                BitmapFactory.Options options) {
        InputStream resourceStream = null;
        Bitmap bitmap = null;
        try {
            resourceStream = resources.openRawResource(resourceId);
            bitmap = BitmapFactory.decodeStream(resourceStream, rect, options);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resourceStream != null) {
                    resourceStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static Bitmap fromAsset(Context context, String assertFilePath,
                                   Rect rect, BitmapFactory.Options options) {
        Bitmap bitmap = null;
        InputStream assertStream = null;
        AssetManager assetManager = context.getAssets();
        try {
            assertStream = assetManager.open(assertFilePath);
            bitmap = BitmapFactory.decodeStream(assertStream, rect, options);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (assertStream != null) {
                    assertStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static Bitmap fromByteArray(byte[] byteArray, int offset, int length,
                                       BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(byteArray, offset, length, options);
    }

    public static Bitmap fromFile(String filePath, BitmapFactory.Options options) {
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap fromDrawable(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity()
                != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        if (bitmap != null) {
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
            return bitmap;
        }
        return null;
    }


    public static Bitmap fromView(View view) {
        view.clearFocus();
        view.setPressed(false);
        boolean willNotCache = view.willNotCacheDrawing();
        view.setWillNotCacheDrawing(false);
        int color = view.getDrawingCacheBackgroundColor();
        view.setDrawingCacheBackgroundColor(color);
        if (color != 0) {
            view.getContentDescription();
        }
        Bitmap cacheBitmap = view.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCache);
        view.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public static Bitmap fromInputStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    public static byte[] toByteArray(Bitmap bitmap, Bitmap.CompressFormat format,
                                     int quality) {
        byte[] bytes = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(format, quality, outputStream);
        bytes = outputStream.toByteArray();
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static Drawable toDrawable(Resources resources, Bitmap bitmap) {
        return new BitmapDrawable(resources, bitmap);
    }

    public static void toFile(Bitmap bitmap, Bitmap.CompressFormat format,
                              int quality, String path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            bitmap.compress(format, quality, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
