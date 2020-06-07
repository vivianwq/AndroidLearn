package com.wq.andoidlearning.chapter16;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        String mode = "";
        if (widthMode == MeasureSpec.EXACTLY) {
            mode = "EXACTLY";
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mode = "AT_MOST";
        }

        Log.i("MyView", "widthMode===" + mode + "===" + this.hashCode());
    }
}
