package com.wq.andoidlearning.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ScrollView;

class BounceScrollView extends ScrollView {
    private static final int MAX_Y_OVER_SCROLL_DISTANCE = 200;
    private Context mContext;
    private int mMaxYOverScrollDistance;

    public BounceScrollView(Context context) {
        super(context);
        mContext = context;
        initBounceListView();
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBounceListView();
    }

    public BounceScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initBounceListView();
    }

    private void initBounceListView() {
        final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverScrollDistance = (int) (density * MAX_Y_OVER_SCROLL_DISTANCE);
        Log.i("BounceListView", "滑动距离" + mMaxYOverScrollDistance);

        //false:隐藏ScrollView的滚动条。
        this.setVerticalScrollBarEnabled(false);
        //不管装载的控件填充的数据是否满屏，都允许橡皮筋一样的弹性回弹。
        this.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {


        //增加阻尼效果,使滑动有吃力感
        double ratio = 1d;
        //在顶部并且是向下拖动
        if (deltaY < 0 && scrollY + deltaY < 0) {
//            ratio = 1.05d + scrollY / (mMaxYOverScrollDistance * 1.2);
            return super.overScrollBy(deltaX, (int) (deltaY * ratio), scrollX, scrollY,
                    scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY,
                    isTouchEvent);
        } else if (deltaY > 0 && scrollY + deltaY > scrollRangeY) { //滑动到底部并且向下滑动
            ratio = 1.05d + (scrollRangeY - scrollY) / (mMaxYOverScrollDistance * 1.2);
            return customOverScrollBy(deltaX, (int) (deltaY * ratio), scrollX, scrollY,
                    scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverScrollDistance,
                    isTouchEvent);
        }
        return super.overScrollBy(deltaX, (int) (deltaY * ratio), scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverScrollDistance,
                isTouchEvent);
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
//                mMaxYOverScrollDistance, isTouchEvent);
    }

    /**
     * 这里在ScrollView中就不用关心横向滑动问题，可以把原来横向逻辑的代码统统删掉，
     * 并且设置默认是可以竖向滑动的
     */
    @SuppressWarnings({"UnusedParameters"})
    protected boolean customOverScrollBy(int deltaX, int deltaY,
                                         int scrollX, int scrollY,
                                         int scrollRangeX, int scrollRangeY,
                                         int maxOverScrollX, int maxOverScrollY,
                                         boolean isTouchEvent) {
        int newScrollX = scrollX + deltaX;
        int newScrollY = scrollY + deltaY;
        // Clamp values if at the limits and record
        final int left = -maxOverScrollX;
        final int right = maxOverScrollX + scrollRangeX;
        final int top = -maxOverScrollY;
        final int bottom = maxOverScrollY + scrollRangeY;
        boolean clampedX = false;
        if (newScrollX > right) {
            newScrollX = right;
            clampedX = true;
        } else if (newScrollX < left) {
            newScrollX = left;
            clampedX = true;
        }
        boolean clampedY = false;
        if (newScrollY > bottom) {
            newScrollY = bottom;
            clampedY = true;
        } else if (newScrollY < top) {
            newScrollY = top;
            clampedY = true;
        }
        onOverScrolled(newScrollX, newScrollY, clampedX, clampedY);
        return clampedX || clampedY;
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        // Not consumed means it wasn't handled because ScrollView
        // doesn't take over scrolling bounds into scroll range,
        // so we fling it ourselves to get it bounce back
        if (!consumed) {
            fling((int) velocityY);
            return true;
        } else {
            return super.dispatchNestedFling(velocityX, velocityY, true);
        }
    }
}