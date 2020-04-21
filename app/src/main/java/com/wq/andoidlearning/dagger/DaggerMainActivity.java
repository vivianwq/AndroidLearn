package com.wq.andoidlearning.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.dagger.demo1.DaggerDemo1Activity;
import com.wq.andoidlearning.dagger.demo2.DaggerDemo2Activity;
import com.wq.andoidlearning.dagger.demo3.DaggerDemo3Activity;
import com.wq.andoidlearning.dagger.demo4.activity.ScopeActivity;

public class DaggerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_main);
        findViewById(R.id.btnDemo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaggerMainActivity.this, DaggerDemo1Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDemo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaggerMainActivity.this, DaggerDemo2Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDemo3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaggerMainActivity.this, DaggerDemo3Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDemo4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaggerMainActivity.this, ScopeActivity.class);
                startActivity(intent);
            }
        });
    }
}
