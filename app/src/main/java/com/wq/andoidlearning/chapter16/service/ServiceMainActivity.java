package com.wq.andoidlearning.chapter16.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class ServiceMainActivity extends AppCompatActivity {
    private TextView tvContent;
    private StringBuilder stringBuilder = new StringBuilder();
    private Intent myService;
    private MyConnect myConnect;

    class MyConnect implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.IService service1 = (MyService.IService) service;
            service1.test();
            EventBus.getDefault().post(new ServiceBean("MyService--onServiceConnected"));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            EventBus.getDefault().post(new ServiceBean("MyService--onServiceDisconnected"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        tvContent = findViewById(R.id.tvContent);
        myConnect = new MyConnect();
        myService = new Intent(ServiceMainActivity.this, MyService.class);
        EventBus.getDefault().register(this);
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(myService);
            }
        });
        findViewById(R.id.btnBind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(myService, myConnect, Service.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btnStartBind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(myService);
                bindService(myService, myConnect, 0);
            }
        });
        findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(myService);
            }
        });
        findViewById(R.id.btnUnBind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(myConnect);
            }
        });
    }

    @Subscriber()
    public void showText(ServiceBean serviceBean) {
        stringBuilder.append(serviceBean.getMsg() + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
