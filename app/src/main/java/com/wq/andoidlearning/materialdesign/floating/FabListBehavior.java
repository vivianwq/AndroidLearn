package com.wq.andoidlearning.materialdesign.floating;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabListBehavior extends FloatingActionButton.Behavior {

    public static final int MIN_CHANGED_DISTANCE = 30;

    public FabListBehavior() {
    }

    public FabListBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > MIN_CHANGED_DISTANCE) {
            createValueAnimator(coordinatorLayout, child, false).start();
        } else if (dyConsumed < -MIN_CHANGED_DISTANCE) {
            createValueAnimator(coordinatorLayout, child, true).start();
        }
    }

    private Animator createValueAnimator(CoordinatorLayout coordinatorLayout,
                                         final View fab, boolean dismiss) {
        int distanceToDismiss = coordinatorLayout.getBottom() - fab.getBottom()
                + fab.getHeight();
        int end = dismiss ? 0 : distanceToDismiss;
        float start = fab.getTranslationY();
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fab.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        return animator;
    }
}
