package com.wq.andoidlearning.component.service.bind;

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
import com.wq.andoidlearning.component.service.start.MyService;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class BindServiceActivity extends AppCompatActivity {

    private TextView tvContent;
    private StringBuilder stringBuilder;
    private Intent myService;
    private IBinderWorker iBinderWorker;
    private MyServiceConnection myServiceConnection;

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderWorker = (IBinderWorker) service;
            stringBuilder.append("BindService--onServiceConnected--" + name);
            tvContent.setText(stringBuilder.toString());


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            stringBuilder.append("BindService--onServiceDisconnected--" + name);
            tvContent.setText(stringBuilder.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        tvContent = findViewById(R.id.tvContent);
        stringBuilder = new StringBuilder();
        EventBus.getDefault().register(this);
        myService = new Intent(BindServiceActivity.this,
                MyService.class);
        myServiceConnection = new MyServiceConnection();

        findViewById(R.id.btnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.putExtra("service", "自定义传递数据");
                bindService(myService, myServiceConnection, Service.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.btnDoSth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iBinderWorker != null) {
                    String bindStr = iBinderWorker.showMsg("通过bind调用了");
                    tvContent.setText(bindStr);
                }
            }
        });

        findViewById(R.id.btnUnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(myServiceConnection);
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
