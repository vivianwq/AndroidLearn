package com.wq.andoidlearning;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    // 是否绘制
    private volatile boolean mIsDrawing;
    // SurfaceView 控制器
    private SurfaceHolder mSurfaceHolder;
    // 画笔
    private Paint mPaint;
    // 画布
    private Canvas mCanvas;
    // 独立的线程
    private Thread mThread;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();
        // 注册回调事件
        mSurfaceHolder.addCallback(this);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread = new Thread(this);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        mIsDrawing = true;
        mThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 不再绘制，移除回调，线程终止
        mIsDrawing = false;
        mSurfaceHolder.removeCallback(this);
        mThread.interrupt();

    }

    @Override
    public void run() {
        while (mIsDrawing) {
            // 锁定画布，获得画布对象
            mCanvas = mSurfaceHolder.lockCanvas();
            try {
                //使用画布做具体的绘制
                draw();
                // 线程休眠 100 ms
                Thread.sleep(100);
            } catch (Exception e) {
            } finally {
                // 解锁画布，提交绘制，显示内容
                if (mCanvas != null) {
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }

    }

    private void draw() {

    }
}
