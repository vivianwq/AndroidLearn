package com.wq.andoidlearning.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;

class BounceListView extends ListView {
    private static final int MAX_Y_OVER_SCROLL_DISTANCE = 200;
    private Context mContext;
    private int mMaxYOverScrollDistance;

    public BounceListView(Context context) {
        super(context);
        mContext = context;
        initBounceListView();
    }

    public BounceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBounceListView();
    }

    public BounceListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initBounceListView();
    }

    private void initBounceListView() {
        final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverScrollDistance = (int) (density * MAX_Y_OVER_SCROLL_DISTANCE);
        Log.i("BounceListView", "滑动距离" + mMaxYOverScrollDistance);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {


        //增加阻尼效果,使滑动有吃力感
        double ratio = 1d;
        //在顶部并且是向下拖动
        if (deltaY < 0 && scrollY + deltaY < 0) {
            ratio = 1.05d + scrollY / (mMaxYOverScrollDistance * 1.2);
        } else if (deltaY > 0 && scrollY + deltaY > scrollRangeY) { //滑动到底部并且向下滑动
            ratio = 1.05d + (scrollRangeY - scrollY) / (mMaxYOverScrollDistance * 1.2);
        }
        return super.overScrollBy(deltaX, (int) (deltaY * ratio), scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverScrollDistance,
                isTouchEvent);
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
//                mMaxYOverScrollDistance, isTouchEvent);
    }
}
