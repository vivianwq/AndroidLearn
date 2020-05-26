package com.wq.andoidlearning.chapter9;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.improve.loop.LogUtils;

public class AnimMainActivity extends AppCompatActivity {

    private ImageView ivBack;
    private AnimationDrawable rocketAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_main);
        ivBack = findViewById(R.id.ivBack);
        findViewById(R.id.btnLottie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimMainActivity.this,LottieActivity.class));
            }
        });
        findViewById(R.id.btnFrameStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimationDrawable drawable = new AnimationDrawable();
                for (int a = 1; a <= 9; a++) {
                    int id = getResources().getIdentifier("audio_anim_0" + a, "mipmap", getPackageName());
                    Drawable da = getResources().getDrawable(id);
                    drawable.addFrame(da, 200);
                }
                ivBack.setBackground(drawable);
                drawable.setOneShot(false);
                //获取对象实例，用来控制播放与停止
                rocketAnimation = (AnimationDrawable) ivBack.getBackground();
                rocketAnimation.start();    // 开启帧动画
            }
        });
        findViewById(R.id.btnFrameStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rocketAnimation != null)
                    rocketAnimation.stop();
            }
        });

        findViewById(R.id.btnAlphaXml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ivBack.startAnimation(AnimationUtils.loadAnimation(AnimMainActivity.this, R.anim.alpha_set));
            }
        });
        findViewById(R.id.btnAlphaCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.clearAnimation();
                ivBack.setBackgroundResource(0);
                AlphaAnimation alpha = new AlphaAnimation(0, 1);
                alpha.setDuration(500);          //设置持续时间
                alpha.setFillAfter(true);                   //动画结束后保留结束状态
                alpha.setInterpolator(new AccelerateInterpolator());        //添加差值器
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ivBack.setAnimation(alpha);
                alpha.start();

            }
        });


        findViewById(R.id.btnScaleXml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ivBack.startAnimation(AnimationUtils.loadAnimation(AnimMainActivity.this, R.anim.scale_set));
            }
        });
        findViewById(R.id.btnScaleCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivBack.clearAnimation();
                ivBack.setBackgroundResource(0);
                ScaleAnimation scale = new ScaleAnimation(1.0f, 0.3f, 1.0f, 0.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scale.setDuration(500);
                scale.setFillAfter(true);
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ivBack.setAnimation(scale);
                scale.start();

            }
        });

        findViewById(R.id.btnTransXml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ivBack.startAnimation(AnimationUtils.loadAnimation(AnimMainActivity.this, R.anim.trans_set));
            }
        });
        findViewById(R.id.btnTransCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.clearAnimation();
                ivBack.setBackgroundResource(0);
                TranslateAnimation translate = new TranslateAnimation(0, 0, 1, 1);
                translate.setDuration(1000);
                translate.setFillAfter(true);
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ivBack.setAnimation(translate);
                translate.start();
            }
        });

        findViewById(R.id.btnRotateXml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ivBack.startAnimation(AnimationUtils.loadAnimation(AnimMainActivity.this, R.anim.rotate_set));
            }
        });
        findViewById(R.id.btnRotateCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivBack.clearAnimation();
                ivBack.setBackgroundResource(0);
                RotateAnimation rotate = new RotateAnimation(0, 270, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(1000);
                rotate.setFillAfter(true);
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ivBack.setAnimation(rotate);
                rotate.start();
            }
        });
        findViewById(R.id.btnAnimatorXML).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.clearAnimation();
                ivBack.setBackgroundResource(0);
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                //关于代码中引用
                AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(AnimMainActivity.this, R.animator.animator_set);
                // 创建组合动画对象  &  加载XML动画
                animator.setTarget(ivBack);
                // 设置动画作用对象
                animator.start();
            }
        });
        findViewById(R.id.btnAnimatorCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.clearAnimation();
                ivBack.setBackgroundResource(0);
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                // 步骤1：设置需要组合的动画效果
                ObjectAnimator translation = ObjectAnimator.ofFloat(ivBack, "translationX", 0, 300, 400);
                // 平移动画
                ObjectAnimator rotate = ObjectAnimator.ofFloat(ivBack, "rotation", 0f, 360f);
                // 旋转动画
                ObjectAnimator alpha = ObjectAnimator.ofFloat(ivBack, "alpha", 1f, 0f, 1f);
                // 透明度动画
                // 步骤2：创建组合动画的对象
                AnimatorSet animSet = new AnimatorSet();
                // 步骤3：根据需求组合动画
                animSet.play(translation).with(rotate).before(alpha);
                animSet.setDuration(5000);
                // 步骤4：启动动画
                animSet.start();
            }
        });
        findViewById(R.id.btnValueAnimatorCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.clearAnimation();
                ivBack.setBackgroundResource(0);
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                ValueAnimator valueAnimator = setValueAnimator(ivBack, 0, 2, 2000, 500, 2);
                valueAnimator.start();
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        LogUtils.i("btnValueAnimatorCode--onAnimationCancel");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        LogUtils.i("btnValueAnimatorCode--onAnimationEnd");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        super.onAnimationRepeat(animation);
                        LogUtils.i("btnValueAnimatorCode--onAnimationRepeat");
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        LogUtils.i("btnValueAnimatorCode--onAnimationStart");
                    }
                });


            }
        });

        findViewById(R.id.btnValueAnimatorXML).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivBack.clearAnimation();
                ivBack.setBackgroundResource(0);
                ivBack.setBackgroundResource(R.mipmap.audio_anim_01);
                Animator mAnim = AnimatorInflater.loadAnimator(AnimMainActivity.this, R.animator.animator_set2);
                mAnim.setTarget(ivBack);
                mAnim.start();
                mAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        LogUtils.i("btnValueAnimatorXML--onAnimationCancel");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        LogUtils.i("btnValueAnimatorXML--onAnimationEnd");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        super.onAnimationRepeat(animation);
                        LogUtils.i("btnValueAnimatorXML--onAnimationRepeat");
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        LogUtils.i("btnValueAnimatorXML--onAnimationStart");
                    }
                });


            }
        });


    }

    public static ValueAnimator setValueAnimator(View view, int start, int end, int time, int delay, int count) {
        // 步骤1：设置动画属性的初始值 & 结束值
        ValueAnimator mAnimator = ValueAnimator.ofInt(start, end);
        // ofInt（）作用有两个
        // 1. 创建动画实例
        // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和1,表示将值从0平滑过渡到1
        // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值 过渡到 结束值
        // 关于自定义插值器我将在下节进行讲解
        // 下面看看ofInt()的源码分析 ->>关注1
        mAnimator.setTarget(view);
        // 步骤2：设置动画的播放各种属性
        mAnimator.setDuration(time);
        // 设置动画运行的时长
        mAnimator.setStartDelay(delay);
        // 设置动画延迟播放时间
        mAnimator.setRepeatCount(count);
        // 设置动画重复播放次数 = 重放次数+1
        // 动画播放次数 = infinite时,动画无限重复
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        // 设置重复播放动画模式
        // ValueAnimator.RESTART(默认):正序重放
        // ValueAnimator.REVERSE:倒序回放
        // 步骤3：将改变的值手动赋值给对象的属性值：通过动画的更新监听器
        // 设置 值的更新监听器
        // 即：值每次改变、变化一次,该方法就会被调用一次
        return mAnimator;
    }
}
