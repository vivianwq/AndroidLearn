package com.wq.andoidlearning.improve.leak;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.lang.ref.WeakReference;

public class LeakActivity extends AppCompatActivity implements OnReceiveMessageListener {
    private Handler handler = new Handler();
    private TextView tvContent;

    private static int threadIndex;
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case 1:
                TextView textView1 = (TextView) msg.obj;
//                    showBottomInAnimation(textView1);
                break;
            case 2:
                TextView textView2 = (TextView) msg.obj;
//                    showBottomOutAnimation(textView2);
                break;
        }
    }

    //自定义handler
    public static class HandlerHolder extends Handler {
        WeakReference<OnReceiveMessageListener> mListenerWeakReference;

        /**
         * @param listener 收到消息回调接口
         */
        HandlerHolder(OnReceiveMessageListener listener) {
            mListenerWeakReference = new WeakReference<>(listener);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mListenerWeakReference != null && mListenerWeakReference.get() != null) {
                mListenerWeakReference.get().handlerMessage(msg);
            }
        }
    }


    private HandlerHolder handler2 = new HandlerHolder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        tvContent = findViewById(R.id.tvContent);
        //创建handler对象


        findViewById(R.id.btnManager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance(LeakActivity.this).dealData();

            }
        });
        findViewById(R.id.btnHandler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        tvContent.setText("通过handler实例改变内容");
                    }
                }, 3000);

            }
        });
        findViewById(R.id.btnThreads).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadIndex++;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int j = threadIndex;
                        while (true) {

                            Log.i("btnThreads","Hi---" + j );
                            tvContent.setText(stringBuilder.toString());
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });

        findViewById(R.id.btnWindowFocus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add监听，放到集合里面
                tvContent.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
                    @Override
                    public void onWindowFocusChanged(boolean b) {
                        //监听view的加载，view加载出来的时候，计算他的宽高等。
                    }
                });
            }
        });
        findViewById(R.id.btnAnim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvContent,"rotation",0,360);
                objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                objectAnimator.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解决内存引用的代码
//        if(handler!=null){
//            handler.removeCallbacksAndMessages(null);
//            handler = null;
//        }
    }
}
