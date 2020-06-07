package com.wq.andoidlearning.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

class DragView extends RelativeLayout {

    //触摸事件
    private int lastX = 0;
    private int lastY = 0;
    private Scroller scroller;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller=new Scroller(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录触摸点的坐标
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int officeX = rawX - lastX;
                int officeY = rawY - lastY;
                //在当前的left,top,right,bottom基础上加上偏移量
//                layout(getLeft() + officeX, getTop() + officeY,
//                        getRight() + officeX, getBottom() + officeY);
//                offsetLeftAndRight(officeX);
//                offsetTopAndBottom(officeY);
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft()+officeX;
//                layoutParams.topMargin = getTop()+officeY;
//                setLayoutParams(layoutParams);
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft()+officeX;
//                layoutParams.topMargin = getTop()+officeY;
//                setLayoutParams(layoutParams);
//                ((View) getParent()).scrollBy(-officeX, -officeY);
                //重新设置初始值
//                lastX = rawX;
//                lastY = rawY;
                break;
            case MotionEvent.ACTION_UP:
                //处理输入的离开动作
                View view = ((View)getParent());
                scroller.startScroll(view.getScrollX(),view.getScrollY(),view.getScrollX()
                        -300,view.getScrollY()-300);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (scroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(scroller.getCurrX(), scroller.getCurrY());
        }
        //通过重绘来不断调用computeScroll
        invalidate();
    }
}
