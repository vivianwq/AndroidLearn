package com.wq.andoidlearning.component;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.activity.BActivity;
import com.wq.andoidlearning.component.broadcastreceiver.BroadCastMainActivity;
import com.wq.andoidlearning.component.contentprovider.ContentProviderActivity;
import com.wq.andoidlearning.component.fragment.FragmentActivity;
import com.wq.andoidlearning.component.service.ServiceActivity;
import com.wq.andoidlearning.improve.loop.LogUtils;

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

        findViewById(R.id.btnActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, BActivity.class);
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
        findViewById(R.id.btnFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        LogUtils.i("AActivity--onCreate");


    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("AActivity--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("AActivity--onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("AActivity--onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("AActivity--onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("AActivity--onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("AActivity--onDestroy");
    }
}
