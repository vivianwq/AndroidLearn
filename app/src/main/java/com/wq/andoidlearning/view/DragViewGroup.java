package com.wq.andoidlearning.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

class DragViewGroup extends FrameLayout {
    //侧滑类
    private ViewDragHelper viewDragHelper;
    private View menuView, mainView;
    private int width;

    public DragViewGroup(@NonNull Context context) {
        super(context);
        initView();
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    //初始化数据
    public void initView() {
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    //XML加载组建后回调
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
    }

    //组件大小改变时回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = menuView.getMeasuredWidth();
    }

    //事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    //触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDragHelper
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    //侧滑回调
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        //何时开始触摸
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            //如果当前触摸的child是mainView开始检测
            return mainView == child;
        }

        //处理水平滑动
        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }

        //处理垂直滑动
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return 0;
        }

        //拖动结束后调用
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指抬起后缓慢的移动到指定位置
            if (mainView.getLeft() < 500) {
                //关闭菜单
                viewDragHelper.smoothSlideViewTo(mainView, 0, 0);
            } else {
                //打开菜单
                viewDragHelper.smoothSlideViewTo(mainView, width, 0);

            }
            ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
        }
    };

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
