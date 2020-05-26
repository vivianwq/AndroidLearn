package com.wq.andoidlearning.improve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.improve.leak.LeakActivity;
import com.wq.andoidlearning.improve.timer.Task;

import java.util.Timer;

public class ImproveMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_main);
        findViewById(R.id.btnLeak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImproveMainActivity.this, LeakActivity.class));
            }
        });
        findViewById(R.id.btnTag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImproveMainActivity.this, TagActivity.class));
            }
        });
        findViewById(R.id.btnTimerTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                Timer t = new Timer();
                t.schedule(task, 2000, 2000);
            }
        });
    }
}
