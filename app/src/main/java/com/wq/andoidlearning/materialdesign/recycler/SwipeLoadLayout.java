package com.wq.andoidlearning.materialdesign.recycler;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewParentCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

import java.util.LinkedList;
import java.util.List;

public class SwipeLoadLayout extends FrameLayout implements NestedScrollingParent, NestedScrollingChild {

    private NestedScrollingParentHelper nestedScrollingParentHelper;
    private NestedScrollingChildHelper nestedScrollingChildHelper;
    //parentView滑动消费的距离
    private final int[] mParentScrollConsumed = new int[2];
    private final int[] mParentOffsetInWindow = new int[2];

    private boolean mNestedScrollInProgress;
    private OnRefreshListener onRefreshListener;
    private OnLoadingListener onLoadingListener;

    private final List<OnRefreshOffsetChangedListener> refreshOffsetChangedListeners = new LinkedList<>();
    private ViewParent mNestedScrollAcceptedParent;

    private FrameLayout headerView;
    private FrameLayout footerView;
    private RecyclerView recyclerView;

    public static final int INVALID = -1;
    public static final int PULL_REFRESH = 0;
    public static final int LOAD_MORE = 1;


    volatile private boolean isRefreshing = false;

    private volatile float refreshViewHeight = 0;
    private volatile float loadingViewHeight = 0;

    public static final float overFlow = 1.0f;

    public static final float DAMPING = 0.4f;

    //Drag Action
    private int currentAction = -1;
    private boolean isConfirm = false;


    public SwipeLoadLayout(Context context) {
        super(context);
    }


    public SwipeLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerView = findViewById(R.id.refreshView);
        footerView = findViewById(R.id.loadView);
        recyclerView = findViewById(R.id.recyclerView);
        initHelper();
    }


    private void initHelper() {

        nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        nestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(false);

        refreshViewHeight = 500;
        loadingViewHeight = 500;

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isEnabled() || canChildScrollUp()
                || isRefreshing || mNestedScrollInProgress) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        nestedScrollingChildHelper.setNestedScrollingEnabled(enabled);

    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return nestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        boolean result = nestedScrollingChildHelper.startNestedScroll(axes);
        if (result) {
            if (mNestedScrollAcceptedParent == null) {
                ViewParent parent = this.getParent();
                View child = this;
                while (parent != null) {
                    if (ViewParentCompat.onStartNestedScroll(parent, child, this, axes)) {
                        mNestedScrollAcceptedParent = parent;
                        break;
                    }
                    if (parent instanceof View) {
                        child = (View) parent;
                    }
                    parent = parent.getParent();
                }
            }
        }
        return result;
    }

    @Override
    public void stopNestedScroll() {
        nestedScrollingChildHelper.stopNestedScroll();
        if (mNestedScrollAcceptedParent != null) {
            mNestedScrollAcceptedParent = null;
        }

    }

    @Override
    public boolean hasNestedScrollingParent() {
        return nestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return nestedScrollingChildHelper.dispatchNestedScroll(dyConsumed,
                dyConsumed, dxConsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return nestedScrollingChildHelper.dispatchNestedPreScroll(dx,
                dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return nestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return nestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        //只处理垂直方向的
        boolean result = isEnabled() && !isRefreshing
                && (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        return result;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        nestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
        if (isNestedScrollingEnabled()) {
            startNestedScroll(axes & ViewCompat.SCROLL_AXIS_VERTICAL);
            mNestedScrollInProgress = true;
        }
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        nestedScrollingParentHelper.onStopNestedScroll(target);
        handlerAction();
        if (isNestedScrollingEnabled()) {
            mNestedScrollInProgress = true;
            stopNestedScroll();
        }

    }

    private void handlerAction() {
        if (isRefreshing()) {
            return;
        }
        isConfirm = false;

        FrameLayout.LayoutParams lp;
        if (currentAction == PULL_REFRESH) {
            lp = (LayoutParams) headerView.getLayoutParams();
            if (lp.height >= refreshViewHeight) {
                startRefresh(lp.height);
            } else if (lp.height > 0) {
                resetHeaderView(lp.height);
            } else {
                resetRefreshState();
            }
        }

        if (currentAction == LOAD_MORE) {
            lp = (LayoutParams) footerView.getLayoutParams();
            if (lp.height >= loadingViewHeight) {
                startLoadmore(lp.height);
            } else if (lp.height > 0) {
                resetFootView(lp.height);
            } else {
                resetLoadMoreState();
            }
        }
    }

    private void startLoadmore(int headerViewHeight) {
        isRefreshing = true;
        ValueAnimator animator = ValueAnimator.ofFloat(headerViewHeight, loadingViewHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LayoutParams lp = (LayoutParams) footerView.getLayoutParams();
                lp.height = (int) ((Float) animation.getAnimatedValue()).floatValue();
                footerView.setLayoutParams(lp);
                moveTargetView(-lp.height);
            }
        });
        animator.addListener(new WXRefreshAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isRefreshing = false;
                if (onLoadingListener != null) {
                    onLoadingListener.onLoading();
                }
            }
        });
        animator.setDuration(300);
        animator.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finishPullLoad();
            }
        }, 1800L);
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void finishPullLoad() {
        if (currentAction == LOAD_MORE) {
            resetFootView(footerView == null ? 0 : footerView.getMeasuredHeight());
        }
    }

    private void resetFootView(int headerViewHeight) {
        ValueAnimator animator = ValueAnimator.ofFloat(headerViewHeight, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LayoutParams lp = (LayoutParams) footerView.getLayoutParams();
                lp.height = (int) ((Float) animation.getAnimatedValue()).floatValue();
                footerView.setLayoutParams(lp);
                moveTargetView(-lp.height);
            }
        });
        animator.addListener(new WXRefreshAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                resetLoadMoreState();

            }
        });
        animator.setDuration(300);
        animator.start();
    }

    private void resetLoadMoreState() {
        isRefreshing = false;
        isConfirm = false;
        currentAction = -1;
    }

    private void startRefresh(final int headerViewHeight) {
        isRefreshing = true;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(headerViewHeight, refreshViewHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                FrameLayout.LayoutParams lp = (LayoutParams) headerView.getLayoutParams();
                lp.height = (int) ((Float) animation.getAnimatedValue()).floatValue();
                notifyOnRefreshOffsetChangedListener(lp.height);
                headerView.setLayoutParams(lp);
                moveTargetView(lp.height);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isRefreshing = false;
                if (onRefreshListener != null) {
                    onRefreshListener.onRefresh();
                }
            }
        });
        valueAnimator.setDuration(300);
        valueAnimator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finishPullRefresh();
            }
        }, 1800L);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (isNestedScrollingEnabled()) {
            dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, mParentOffsetInWindow);
        }
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {

        final int[] parentConsumed = mParentScrollConsumed;
        if (isNestedScrollingEnabled()) {
            if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, null)) {
                consumed[0] += parentConsumed[0];
                consumed[1] += parentConsumed[1];
                return;
            }
        }

        if (!canChildScrollUp() && isNestedScrollingEnabled()) {
            if (mNestedScrollAcceptedParent != null && mNestedScrollAcceptedParent != recyclerView) {
                ViewGroup group = (ViewGroup) mNestedScrollAcceptedParent;
                if (group.getChildCount() > 0) {
                    int count = group.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View view = group.getChildAt(i);
                        if (view.getVisibility() != View.GONE && view.getMeasuredHeight() > 0) {
                            if (view.getTop() < 0) {
                                return;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }

        int spinnerDy = (int) calculateDistanceY(target, dy);
        isRefreshing = false;
        if (!isConfirm) {
            if (spinnerDy < 0 && !canChildScrollUp()) {
                currentAction = PULL_REFRESH;
                isConfirm = true;
            } else if (spinnerDy > 0 && !canChildScrollDown() && (!isRefreshing)) {
                currentAction = LOAD_MORE;
                isConfirm = true;
            }
        }

        if (moveSpinner(-spinnerDy)) {
            if (!canChildScrollUp()
                    && recyclerView.getTranslationY() > 0
                    && dy > 0) {
                consumed[1] += dy;
            } else if (!canChildScrollDown()
                    && recyclerView.getTranslationY() < 0
                    && dy < 0) {
                consumed[1] += dy;
            } else {
                consumed[1] += spinnerDy;
            }

        }
    }

    public boolean canChildScrollUp() {
        if (recyclerView == null) {
            return false;
        }
        return ViewCompat.canScrollVertically(recyclerView, -1);
    }

    public void finishPullRefresh() {
        if (currentAction == PULL_REFRESH) {
            resetHeaderView(headerView == null ? 0 : headerView.getMeasuredHeight());
        }
    }

    private void resetHeaderView(int headerViewHeight) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(headerViewHeight, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                FrameLayout.LayoutParams lp = (LayoutParams) headerView.getLayoutParams();
                lp.height = (int) ((Float) animation.getAnimatedValue()).floatValue();
                notifyOnRefreshOffsetChangedListener(lp.height);
                headerView.setLayoutParams(lp);
                moveTargetView(lp.height);
            }
        });

        valueAnimator.addListener(new WXRefreshAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                resetRefreshState();
            }
        });
        valueAnimator.setDuration(300);
        valueAnimator.start();
    }

    private void resetRefreshState() {
        isRefreshing = false;
        isConfirm = false;
        currentAction = -1;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnLoadingListener(OnLoadingListener onLoadingListener) {
        this.onLoadingListener = onLoadingListener;
    }

    public boolean canChildScrollDown() {
        if (recyclerView == null) {
            return false;
        }
        return ViewCompat.canScrollVertically(recyclerView, 1);
    }

    private boolean moveSpinner(float distanceY) {
        if (isRefreshing) {
            return false;
        }
        if (!canChildScrollUp() && currentAction == PULL_REFRESH) {
            FrameLayout.LayoutParams layoutParams = (LayoutParams) headerView.getLayoutParams();
            layoutParams.height += distanceY;
            if (layoutParams.height < 0) {
                layoutParams.height = 0;
            }
            if (layoutParams.height == 0) {
                isConfirm = false;
                currentAction = INVALID;
            }
            headerView.setLayoutParams(layoutParams);
            notifyOnRefreshOffsetChangedListener(layoutParams.height);
            moveTargetView(layoutParams.height);
            return true;
        } else if (!canChildScrollDown() && currentAction == LOAD_MORE) {
            FrameLayout.LayoutParams layoutParams = (LayoutParams) footerView.getLayoutParams();
            layoutParams.height -= distanceY;
            if (layoutParams.height < 0) {
                layoutParams.height = 0;
            }
            if (layoutParams.height == 0) {
                isConfirm = false;
                currentAction = INVALID;
            }
            footerView.setLayoutParams(layoutParams);
            moveTargetView(-layoutParams.height);
            return true;
        }
        return false;
    }

    private void notifyOnRefreshOffsetChangedListener(int verticalOffset) {
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        if (isNestedScrollingEnabled()) {
            return dispatchNestedFling(velocityX, velocityY, consumed);
        }
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        if (isNestedScrollingEnabled()) {
            return dispatchNestedPreFling(velocityX, velocityY);
        }
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return nestedScrollingParentHelper.getNestedScrollAxes();
    }

    private double calculateDistanceY(View target, int dy) {
        int viewHeight = target.getMeasuredHeight();
        //比例
        double ratio = (viewHeight - Math.abs(target.getY())) / 1.0d / viewHeight * DAMPING;
        if (ratio <= 0.01d) {
            ratio = 0.01d;
        }
        return ratio * dy;
    }

    private void moveTargetView(float h) {
        recyclerView.setTranslationY(h);
    }

    public interface OnRefreshListener {
        void onRefresh();

        void onPullingDown(float dy, int pullOutDistance, float viewHeight);
    }


    public interface OnLoadingListener {
        void onLoading();

        void onPullingUp(float dy, int pullOutDistance, float viewHeight);
    }


    public interface OnRefreshOffsetChangedListener {
        void onOffsetChanged(int verticalOffset);
    }

    static class WXRefreshAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }

}
