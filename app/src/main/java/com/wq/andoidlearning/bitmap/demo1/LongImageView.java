package com.wq.andoidlearning.bitmap.demo1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.io.IOException;
import java.io.InputStream;

/**
 * 加载长图的ImageView
 * 1.支持滑动的长图加载
 * 2.支持fling
 * 3.#支持单指拖动,双击放大,手势放大,拖动旋转等
 * 思路
 * 1.只加载与屏幕内的图像信息并显示,所以,当图片的宽高均超过
 * 屏幕的宽高的时候,就会涉及到图片的缩放
 * 2.将当前需要显示的图片的位置信息存储于Rect中,然后在onDraw方法中,
 * 将图片信息画至对应的区域
 * 3.监听手势及触摸事件处理滑动及fling事件
 */

public class LongImageView extends View implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private final Context context;
    private int imageWidth;
    private int imageHeight;
    private Scroller scroller;
    private GestureDetector gestureDetector;
    private BitmapRegionDecoder bitmapRegionDecoder;
    private BitmapFactory.Options options;
    private Rect rect;
    private int viewWidth;
    private int viewHeight;
    private float scale;
    private Bitmap bitmap;

    public LongImageView(Context context) {
        this(context, null);
    }

    public LongImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LongImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    /**
     * 初始化
     */
    private void init() {
        scroller = new Scroller(context);
        //手势监听
        gestureDetector = new GestureDetector(context, this);
        options = new BitmapFactory.Options();
        //保存需要绘制的区域
        rect = new Rect();
        setOnTouchListener(this);
    }


    /**
     * 第二步,先拿到与图片处理
     *
     * @param inputStream
     */
    public void setImage(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        //1.先拿到图片对应的宽高
        //只获取图片的尺寸信息
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        //拿到图片的宽高
        imageWidth = options.outWidth;
        imageHeight = options.outHeight;
        //开启复用
        options.inMutable = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        //区域解码器
        try {
            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("LongImageView", "decoder-exception-->" + e.getLocalizedMessage());
        }
        options.inJustDecodeBounds = false;
    }

    /**
     * 第三步,拿到当前view的尺寸,并计算缩放系数
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        rect.top = rect.left = 0;
        rect.right = viewWidth;
        scale = imageWidth / (float) viewWidth;
        rect.bottom = (int) (imageHeight / scale);
        //注意缩放的特殊情况,小于1
        if (scale <= 1) {
            rect.bottom = viewHeight;
        }
    }

    /**
     * 第四步,在内容区域画出具体的内容
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmapRegionDecoder == null) {
            throw new RuntimeException("请设置图片");
        }
        options.inBitmap = bitmap;
        bitmap = bitmapRegionDecoder.decodeRegion(rect, options);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        canvas.drawBitmap(bitmap, matrix, null);
    }

    /**
     * 第五步,处理触摸事件,统一交给GestureDector处理
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //直接交给detector处理
        return gestureDetector.onTouchEvent(event);
    }

    /**
     * 第六步,down事件需要返回true,代表消费此事件
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDown(MotionEvent e) {
        //触摸时,如果还在滚动,就强制的停止滚动
        if (scroller != null && !scroller.isFinished()) {
            scroller.forceFinished(true);
        }
        return true;
    }


    /**
     * 第七步,处理滚动,滚动时,需要动态更新mRect并重绘来保证
     * 图片对应的区域能绘制到view中
     *
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        rect.offset(0, (int) distanceY);
        if (rect.bottom > imageHeight) {
            rect.bottom = imageHeight;
            if (scale == 1) {
                rect.bottom = viewHeight;
            }
            rect.top = imageHeight - ((int) (viewHeight / scale));
        }
        if (rect.top < 0) {
            rect.top = 0;
            rect.bottom = ((int) (viewHeight / scale));
        }
        invalidate();
        return false;
    }


    /**
     * 第八步,处理惯性事件
     *
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        scroller.fling(0, rect.top,
                ((int) velocityX), -((int) velocityY),
                0, 0, 0,
                imageHeight - ((int) (imageHeight / scale)));
        return false;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller == null) {
            return;
        }
        if (scroller.computeScrollOffset()) {
            rect.top = scroller.getCurrY();
            rect.bottom = scroller.getCurrY() +
                    ((int) (viewHeight / scale));
            invalidate();
        }
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.e("LongImageView", "onShowPress");
    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.e("LongImageView", "onSingleTapUp");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.e("LongImageView", "onLongPress");
    }
}
