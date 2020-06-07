package com.wq.andoidlearning.chapter16;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.chapter16.fragment.FragmentStateActivity;
import com.wq.andoidlearning.chapter16.service.ServiceMainActivity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

class MyIns {

    public Context context;
    private static MyIns myIns;

    private MyIns(Context context) {
        this.context = context;

    }

    public static MyIns getInstance(Context context) {
        if (myIns == null) {
            myIns = new MyIns(context);
        }
        return myIns;
    }
}

public class MainMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        findViewById(R.id.btnComponent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMainActivity.this, Main1Activity.class));
            }
        });
        findViewById(R.id.btnStartTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMainActivity.this, Main3Activity.class));
            }
        });

        findViewById(R.id.btnService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMainActivity.this, ServiceMainActivity.class));
            }
        });

        findViewById(R.id.btnFragmentState).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMainActivity.this, FragmentStateActivity.class));
            }
        });
//        myIns = MyIns.getInstance(MainMainActivity.this);
//        myIns.context=null;
//        myIns=null;
        Message message = Message.obtain();
        mHandler.sendMessageDelayed(message,4000);

    }

    private WeakReference<Activity> aa;
    //    private MyIns myIns;
    private ReferenceQueue queue;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();

//        mHandler.removeCallbacksAndMessages(null);
//        queue = new ReferenceQueue();
//        aa = new WeakReference(MainMainActivity.this, queue);
//        Log.i("ReferenceQueue", "gc前" + aa.get() + "引用链监听");
//        Reference poll = null;
//        Runtime.getRuntime().gc();
//        System.runFinalization();
//        Log.i("ReferenceQueue", "gc后" + aa.get() + "引用链监听");
////                while ((poll = queue.poll()) != null) {
//                    Log.i("ReferenceQueue", "gc队列数据对象" + poll.toString());
//                }


        //弱引用的强引用对象 不设为null,引用链里就不可能有该对象.


    }

    private MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {

        public WeakReference<Context> reference;

        public MyHandler(Context context) {
            reference = new WeakReference<>(context);//这里传入activity的上下文
        }

        @Override
        public void handleMessage(Message msg) {
            MainMainActivity activity = (MainMainActivity) reference.get();
            if (activity != null) {
                Log.i("ReferenceQueue", "没有回收");
            }else{
                Log.i("ReferenceQueue", "Object has been collected.");
            }
        }


    }


}
