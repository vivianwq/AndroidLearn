package com.wq.andoidlearning.component;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.broadcastreceiver.BroadCastMainActivity;
import com.wq.andoidlearning.component.contentprovider.ContentProviderActivity;
import com.wq.andoidlearning.component.service.ServiceActivity;

public class ComponentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_main);

        findViewById(R.id.btnService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnContentProvider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, ContentProviderActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBroadcastReceiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, BroadCastMainActivity.class);
                startActivity(intent);
            }
        });




    }
}
