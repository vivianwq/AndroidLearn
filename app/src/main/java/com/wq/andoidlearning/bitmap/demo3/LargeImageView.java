package com.wq.andoidlearning.bitmap.demo3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import java.io.IOException;
import java.io.InputStream;

public class LargeImageView extends View implements GestureDetector.OnGestureListener {

    public static final String TAG = "LargeImageView";
    private BitmapRegionDecoder bitmapRegionDecoder;

    //绘制的区域
    private volatile Rect rect = new Rect();

    private int scaledTouchSlop;

    //分别记录上次滑动的坐标
    private int lastY = 0;
    private int lastX = 0;

    //图片的宽度和高度
    private int imageWidth, imageHeight;
    //手势控制器
    private GestureDetector gestureDetector;
    private BitmapFactory.Options options;

    public LargeImageView(Context context) {
        this(context, null);
    }

    public LargeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LargeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        //设置显示图片的参数,如果对图片质量有要求,就选择ARGB_8888模式
        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.d(TAG, "sts:" + scaledTouchSlop);
        //初始化手势控制器
        gestureDetector = new GestureDetector(context, this);
        //获取图片的宽高
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open("b.jpg");
            //初始化BitmapRegionDecode,并用来显示图片
            //如果在decodeStream之前使用is,会导致出错
            //此时流的起始位置已经被移动过了,需要调用is.reset()来重置,
            //然后再decodeStream(imgInputStream, null, options)
            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            inputStream.reset();
            BitmapFactory.decodeStream(inputStream, null, options);
            imageWidth = options.outWidth;
            imageHeight = options.outHeight;
            Log.e(TAG, "width:" + imageWidth + ",height:" + imageHeight);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //把触摸事件交给手势控制器处理
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        lastX = (int) e.getRawX();
        lastY = (int) e.getRawY();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        int x = (int) e2.getRawX();
        int y = (int) e2.getRawY();
        move(x,y);
        return true;
    }

    private void move(int x, int y) {
        boolean isInvalidate = false;

        int deltaX = x - lastX;
        int deltaY = y - lastY;
        Log.d(TAG, "move,deltaX:" + deltaX + " deltaY:" + deltaY);
        //如果图片宽度大于屏幕宽度
        if (imageWidth > getWidth()) {
            //移动rect区域
            rect.offset(-deltaX, 0);
            //检查是否到达图片最右端
            if (rect.right > imageWidth) {
                rect.right = imageWidth;
                rect.left = imageWidth - getWidth();
            }
            //检查左端
            if (rect.left < 0) {
                rect.left = 0;
                rect.right = getWidth();
            }
            isInvalidate = true;
        }
        //如果图片高度大于屏幕高度
        if (imageHeight > getHeight()) {
            rect.offset(0, -deltaY);
            //是否到达最底部
            if (rect.bottom > imageHeight) {
                rect.bottom = imageHeight;
                rect.top = imageHeight - getHeight();
            }
            if (rect.top < 0) {
                rect.top = 0;
                rect.bottom = getHeight();
            }
            isInvalidate = true;
        }
        if (isInvalidate) {
            invalidate();
        }
        lastX = x;
        lastY = y;

    }

    @Override
    public void onLongPress(MotionEvent e) {

        lastX = (int) e.getRawX();
        lastY = (int) e.getRawY();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int x = (int) e2.getRawX();
        int y = (int) e2.getRawY();
        move(x, y);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //显示图片
        Bitmap bitmap = bitmapRegionDecoder.decodeRegion(rect, options);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int mImageWidth = imageWidth;
        int mImageHeight = imageHeight;

        //默认显示图片的中心区域
        rect.left = mImageWidth / 2 - width / 2;
        rect.top = mImageHeight / 2 - height / 2;
        rect.right = rect.left + width;
        rect.bottom = rect.top + height;
    }
}
