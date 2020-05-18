package com.wq.andoidlearning.component.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class BroadCastMainActivity extends AppCompatActivity {
    private NetBroadcastReceiver netBroadcastReceiver;
    private TextView tvContent;
    private StringBuilder stringBuilder;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_main);
        EventBus.getDefault().register(this);
        localBroadcastManager = LocalBroadcastManager.getInstance(this); // 获取实例
        stringBuilder = new StringBuilder();
        tvContent = findViewById(R.id.tvContent);
        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (netBroadcastReceiver == null) {
                    netBroadcastReceiver = new NetBroadcastReceiver();
                }
                IntentFilter filter = new IntentFilter();
                filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                registerReceiver(netBroadcastReceiver, filter);
            }
        });
        findViewById(R.id.btnSendSys).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent("android.net.conn.CONNECTIVITY_CHANGE");
//                sendBroadcast(intent); // 发送广播
                showText(new ServiceBean("Android4.4版本后保护了一些广播，不允许系统之外的应用发送这些广播，可以AndroidManifest.xml 中看到这条权限被保护了，只有系统能发送"));
            }
        });
        findViewById(R.id.btnUnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(netBroadcastReceiver);
            }
        });
        findViewById(R.id.btnSendNormal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringBuilder = new StringBuilder();
                Intent intent = new Intent();
                intent.setAction("com.wq.broadcastReceiverType");
                // 下面这句话很重要，9.0以后必须要加这段代码不然收不到广播！！！
                intent.setPackage(getPackageName());

                sendBroadcast(intent, null);
            }
        });
        findViewById(R.id.btnSendOrdered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringBuilder = new StringBuilder();
                Intent intent = new Intent();
                intent.setAction("com.wq.broadcastReceiverType");
                // 下面这句话很重要，9.0以后必须要加这段代码不然收不到广播！！！
                intent.setPackage(getPackageName());
                sendOrderedBroadcast(intent, null);
            }
        });
        findViewById(R.id.btnSendSticky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendStickyBroadcast(new Intent("com.wq.stickybrocast"));  //发送粘性广播
                sendBroadcast(new Intent("com.wq.normalbrocast"));
            }
        });
        findViewById(R.id.btnRegisterNormal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(normalReceiver, new IntentFilter("com.wq.normalbrocast")); //注册粘性广播接收器

            }
        });
        findViewById(R.id.btnRegisterSticky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(stickReceiver, new IntentFilter("com.wq.stickybrocast")); //注册粘性广播接收器

            }
        });
        findViewById(R.id.btnLocalBroadCast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.wq.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent); // 发送本地广播
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.wq.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }


    @Subscriber()
    public void showText(ServiceBean serviceBean) {
        stringBuilder.append(serviceBean.getMsg() + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (localReceiver != null) {
            localBroadcastManager.unregisterReceiver(localReceiver);
        }
        unregisterReceiver(stickReceiver);
        unregisterReceiver(normalReceiver);
        if (netBroadcastReceiver != null) {
            unregisterReceiver(netBroadcastReceiver);
        }
    }

    BroadcastReceiver stickReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            tvContent.setText("收到粘性广播通知");
        }
    };
    BroadcastReceiver normalReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            tvContent.setText("收到普通广播通知");
        }
    };

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvContent.setText("收到本地广播通知");
        }
    }
}
