package com.wq.andoidlearning.component.fragment.androidx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class AndroidXFragmentActivity extends AppCompatActivity {

    private Fragment1 fragment1;
    private TextView tvContent;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_x_fragment);
        EventBus.getDefault().register(this);
        fragment1 = Fragment1.newInstance();
        tvContent = findViewById(R.id.tvContent);
        stringBuilder=new StringBuilder();
        findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCreated();
            }
        });

        findViewById(R.id.btnResumed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResumed();
            }
        });

        findViewById(R.id.btnStarted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStarted();
            }
        });
        findViewById(R.id.btnStartAlready).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStartedAlready();
            }
        });
        findViewById(R.id.btnResumedAlready).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResumedAlready();
            }
        });
        findViewById(R.id.btnNewLazy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AndroidXFragmentActivity.this,
                        NewFragmentVPActivity.class));
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void setCreated() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment1);
        ft.setMaxLifecycle(fragment1, Lifecycle.State.CREATED);
        ft.commit();
    }

    public void setResumed() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment1);
        ft.commit();
        ft = getSupportFragmentManager().beginTransaction();
        ft.setMaxLifecycle(fragment1, Lifecycle.State.CREATED);
        ft.commit();
    }

    public void setStarted() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment1);
        ft.setMaxLifecycle(fragment1, Lifecycle.State.STARTED);
        ft.commit();
    }

    public void setStartedAlready() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment1);
        ft.commit();
        ft=getSupportFragmentManager().beginTransaction();
        ft.setMaxLifecycle(fragment1, Lifecycle.State.STARTED);
        ft.commit();
    }

    public void setResumedAlready(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setMaxLifecycle(fragment1, Lifecycle.State.RESUMED);
        ft.commit();
    }

    @Subscriber
    public void showText(ServiceBean serviceBean) {
        stringBuilder.append(serviceBean.getMsg());
        tvContent.setText(stringBuilder.toString());
    }
}
