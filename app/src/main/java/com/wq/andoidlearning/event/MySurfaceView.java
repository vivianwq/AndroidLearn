package com.wq.andoidlearning.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

import static com.wq.andoidlearning.event.MyLinearLayout.getType;

public class MySurfaceView extends SurfaceView {
    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVisibility(View.GONE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean b = true;
        EventBus.getDefault().post(new ServiceBean("MySurfaceView--dispatchTouchEvent--" + b + "===" + getType(event)));
        super.dispatchTouchEvent(event);

        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = true;
        EventBus.getDefault().post(new ServiceBean("MySurfaceView--onTouchEvent--" + b + "===" + getType(event)));

        super.onTouchEvent(event);

        return b;
    }
}
