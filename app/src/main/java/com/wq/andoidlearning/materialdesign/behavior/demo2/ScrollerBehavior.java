package com.wq.andoidlearning.materialdesign.behavior.demo2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView的Behavior
 */
public class ScrollerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    public ScrollerBehavior() {
    }

    public ScrollerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull RecyclerView child, @NonNull View dependency) {
        // 如果我们所依赖的View有变化，也是通过offsetTopAndBottom移动我们的RecyclerView
        ViewCompat.offsetTopAndBottom(child, (dependency.getBottom() - child.getTop()));
        return false;
    }
}
