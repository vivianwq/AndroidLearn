package com.wq.andoidlearning.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

import static com.wq.andoidlearning.event.MyLinearLayout.getType;

public class MyView extends View {
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = true;

        EventBus.getDefault().post(new ServiceBean("MyView--onTouchEvent--" + b + "===" + getType(event)));

//        super.onTouchEvent(event);

        return true;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean b = true;
//        EventBus.getDefault().post(new ServiceBean("MyView--dispatchTouchEvent--" + b + "===" + getType(ev)));
////        super.dispatchTouchEvent(ev);
//
//        return b;
//    }


}
