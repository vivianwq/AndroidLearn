package com.wq.andoidlearning.materialdesign.behavior.demo1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

public class DependencyBehavior1 extends CoordinatorLayout.Behavior<View> {

    private float deltaY;

    public DependencyBehavior1() {
    }

    public DependencyBehavior1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //child就是需要变换的View
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        //这里表示 我们需要依赖RecyclerView
        boolean isDependency = dependency instanceof RecyclerView;
        if (isDependency) {
            RecyclerView recyclerView = (RecyclerView) dependency;
        }
        return isDependency;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        // 获取到RecyclerView的Y坐标
        float dependencyY = dependency.getY();
        if (deltaY == 0) {
            // 第一次先获取到初始状态下RecyclerView的Y坐标和绑定的View的高度差值作为后面计算的基值

            deltaY = dependencyY - child.getHeight();
        }
        // 根据RecyclerView移动，计算当前的差值
        float dy = dependencyY - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        // 求出当前需要移动的距离
        float y = -(dy / deltaY) * child.getHeight();
        float preTranslationY = child.getTranslationY();
        if (y != preTranslationY) {
            // 移动HelloWorld 并返回true
            child.setTranslationY(y);
            return true;
        }
        return false;
    }
}
