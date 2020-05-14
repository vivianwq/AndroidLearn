package com.wq.andoidlearning.component.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class BroadCastMainActivity extends AppCompatActivity {
    private NetBroadcastReceiver netBroadcastReceiver;
    private TextView tvContent;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_main);
        EventBus.getDefault().register(this);
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
    }

    @Subscriber()
    public void showText(ServiceBean serviceBean) {
        stringBuilder.append(serviceBean.getMsg() + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
