package com.wq.andoidlearning.chapter9;

import android.animation.Animator;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.wq.andoidlearning.R;
import com.wq.andoidlearning.improve.loop.LogUtils;

public class LottieActivity extends AppCompatActivity {
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        lottieAnimationView = findViewById(R.id.lottie_view);
        initAnim();
    }

    /**
     * 初始化动画操作
     */
    private void initAnim() {
        try {
            //None无缓存
            //在assets目录下的动画json文件名。
            lottieAnimationView.setAnimation("Lottie Logo 1.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

    /*LottieComposition.Factory.fromAssetFileName(this, "loading.json",
            new OnCompositionLoadedListener() {
        @Override
        public void onCompositionLoaded(@Nullable LottieComposition composition) {
            if (composition != null) {
                mLottieView.setComposition(composition);
            }
            mLottieView.setProgress(0.333f);
            mLottieView.playAnimation();
        }
    });*/

        //设置动画循环播放
        lottieAnimationView.loop(false);
        //assets目录下的子目录，存放动画所需的图片
        //mLottieView.setImageAssetsFolder("anim/");
        //播放动画
        //mLottieView.playAnimation();
        //是否在播放
        //boolean animating = mLottieView.isAnimating();
        //播放
        //mLottieView.playAnimation();
        //暂停
        //mLottieView.pauseAnimation();
        //取消
        //mLottieView.cancelAnimation();
        //获取动画时长
        lottieAnimationView.getDuration();
        //添加动画监听
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                LogUtils.d("addAnimatorListener---" + "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LogUtils.d("addAnimatorListener---" + "onAnimationEnd");
//                toMain();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                LogUtils.d("addAnimatorListener---" + "onAnimationCancel");
//                toMain();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                LogUtils.d("addAnimatorListener---" + "onAnimationRepeat");
            }
        });
        startAnimating();
    }


    /**
     * 开始动画
     */
    private void startAnimating() {
        boolean inPlaying = lottieAnimationView.isAnimating();
        if (!inPlaying) {
            lottieAnimationView.setProgress(0f);
            lottieAnimationView.playAnimation();
        }
    }

    /**
     * 停止动画
     */
    private void stopAnimating() {
        boolean inPlaying = lottieAnimationView.isAnimating();
        if (inPlaying) {
            lottieAnimationView.cancelAnimation();
        }
    }

    /**
     * 取消动画
     */
    @Override
    protected void onStop() {
        super.onStop();
        stopAnimating();
    }

    /**
     * 注意销毁的时候移除监听
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lottieAnimationView != null) {
            lottieAnimationView.removeAllLottieOnCompositionLoadedListener();
            lottieAnimationView.removeAllAnimatorListeners();
            lottieAnimationView.removeAllLottieOnCompositionLoadedListener();
        }
    }
}
