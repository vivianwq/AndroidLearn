package com.wq.andoidlearning.materialdesign.nested;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.ViewCompat;

public class ParentView extends FrameLayout implements NestedScrollingParent2 {


    private View imageRight;
    private View imageLeft;

    public ParentView(Context context) {
        this(context, null);
    }

    public ParentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageRight = getChildAt(1);
        imageLeft = getChildAt(2);
        //第二个对象是附加滑动追随对象
        Log.i("ParentView", imageRight.toString());
        //第一个子view就是滑动对象
        Log.i("ParentView", getChildAt(0).toString());
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        //如果是竖直方向滑动,就启动嵌套滑动
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        //这里的Consumed代表NestScrollView消耗的距离,Unconsumed代表NestScrollView未消耗的距离
        //imageRight根据NestScrollView滑动的距离而进行相应的滑动
        imageRight.setTranslationY(imageRight.getTranslationY() + dyConsumed);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        imageLeft.setTranslationY(imageLeft.getTranslationY()+dy);
    }
}
