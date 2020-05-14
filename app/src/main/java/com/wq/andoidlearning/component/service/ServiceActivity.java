package com.wq.andoidlearning.component.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.service.bind.BindServiceActivity;
import com.wq.andoidlearning.component.service.intent.IntentServiceActivity;
import com.wq.andoidlearning.component.service.start.StartServiceActivity;

import org.simple.eventbus.EventBus;

public class ServiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        EventBus.getDefault().register(this);
        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceActivity.this,
                        StartServiceActivity.class));
            }
        });

        findViewById(R.id.btnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceActivity.this,
                        BindServiceActivity.class));
            }
        });
        findViewById(R.id.btnIntentService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceActivity.this,
                        IntentServiceActivity.class));
            }
        });

    }


}
