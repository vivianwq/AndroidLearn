package com.wq.andoidlearning.materialdesign.nested.demo2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;

import com.wq.andoidlearning.R;

//模仿饿了么
public class ElemeDetailView extends LinearLayout implements NestedScrollingParent {
    private View edvTitle, edvHeader, edvContent;
    private int titleHeight, headerHeight;
    private NestedScrollingParentHelper nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    private Listener listener;

    public ElemeDetailView(Context context) {
        this(context, null);
    }

    public ElemeDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        edvTitle = findViewById(R.id.edvTitle);
        edvHeader = findViewById(R.id.edvHeader);
        edvContent = findViewById(R.id.edvContent);

        //监听edvContent的位置改变
        //edvContent的移动范围为titleHeight-titleHeight+headerHeight
        //据此算出一个百分比
        edvContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (listener != null) {
                    float fraction = (edvContent.getY() - titleHeight) / headerHeight;
                    listener.onContentPositionChanged(fraction);
                }
                return true;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        titleHeight = edvTitle.getMeasuredHeight();
        headerHeight = edvHeader.getMeasuredHeight();
        //重新测量高度
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight() + headerHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onContentPositionChanged(float fraction);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        float supposeY = edvContent.getY() - dy;//希望edvContent移动到的位置
        //往上滑,y的边界为titleHeight
        if (dy > 0) {
            if (supposeY >= titleHeight) {
                offset(dy, consumed);
            } else {
                offset((int) (edvContent.getY() - titleHeight), consumed);
            }
        }

        //往下滑,y的边界为titleHeight+headerHeight
        if (dy < 0) {
            if (!ViewCompat.canScrollHorizontally(target, dy)) {
                if (supposeY <= titleHeight + headerHeight) {
                    //当target不能向下滑时
                    offset(dy, consumed);
                } else {
                    offset((int) (edvContent.getY() - titleHeight-headerHeight), consumed);
                }
            }
        }
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        nestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    private void offset(int dy, int[] consumed) {
        //第二个参数为正代表向下,为负代表向上
        ViewCompat.offsetTopAndBottom(edvContent, -dy);
        consumed[0] = 0;
        consumed[1] = dy;
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onStopNestedScroll(View child) {
        nestedScrollingParentHelper.onStopNestedScroll(child);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return nestedScrollingParentHelper.getNestedScrollAxes();
    }
}
