package com.wq.andoidlearning.materialdesign.behavior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.materialdesign.behavior.demo1.Behavior1Activity;
import com.wq.andoidlearning.materialdesign.behavior.demo2.Behavior2Activity;
import com.wq.andoidlearning.materialdesign.behavior.demo3.BehaviorActivity;

public class BehaviorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_main);
        findViewById(R.id.behavior1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BehaviorMainActivity.this, Behavior1Activity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.behavior2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BehaviorMainActivity.this, Behavior2Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.behavior3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BehaviorMainActivity.this, BehaviorActivity.class);
                startActivity(intent);
            }
        });
    }
}
