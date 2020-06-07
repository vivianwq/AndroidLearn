package com.wq.andoidlearning.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static String getType(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
            default:
                return "MotionEvent";
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = false;
        EventBus.getDefault().post(new ServiceBean("MyLinearLayout--onTouchEvent--" + b + "===" + getType(event)));
        super.onTouchEvent(event);
        return b;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean consume = false;//事件是否被消费
        if (onInterceptTouchEvent(ev)){//调用onInterceptTouchEvent判断是否拦截事件
            consume = onTouchEvent(ev);//如果拦截则调用自身的onTouchEvent方法
        }else{
//            consume = child.dispatchTouchEvent(ev);//不拦截调用子View的dispatchTouchEvent方法
        }
        return consume;//返回值表示事件是否被消费，true事件终止，false调用父View的onTouchEvent方法
    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean b = true;
//        EventBus.getDefault().post(new ServiceBean("MyLinearLayout--dispatchTouchEvent--" + b + "===" + getType(ev)));
//
//
//        super.dispatchTouchEvent(ev);
//        return b;
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean b = false;
//        EventBus.getDefault().post(new ServiceBean("MyLinearLayout--onInterceptTouchEvent--" + b + "===" + getType(ev)));
//        super.onInterceptTouchEvent(ev);
//        return false;
//    }
}
