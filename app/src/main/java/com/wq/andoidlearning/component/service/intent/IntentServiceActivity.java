package com.wq.andoidlearning.component.service.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class IntentServiceActivity extends AppCompatActivity {

    private TextView tvContent;
    private StringBuilder stringBuilder;
    private Intent myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        tvContent = findViewById(R.id.tvContent);
        stringBuilder = new StringBuilder();
        EventBus.getDefault().register(this);
        myService = new Intent(IntentServiceActivity.this,
                MyIntentService.class);
        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.putExtra("service", "自定义传递数据");
                startService(myService);
            }
        });

        findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(myService);
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
