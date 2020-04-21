package com.wq.andoidlearning.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.rxjava.demo10.RxJavaDemo10Activity;
import com.wq.andoidlearning.rxjava.demo11.RxJavaDemo11Activity;
import com.wq.andoidlearning.rxjava.demo4.RxJavaDemo4Activity;

public class RxJavaDemoMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo_main);
        findViewById(R.id.btnDemo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo1Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDemo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo2Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDemo3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo3Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDemo4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo4Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDemo5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo5Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDemo6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo6Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDemo7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo7Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDemo8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo8Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDemo9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo9Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnDemo10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo10Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDemo11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxJavaDemoMainActivity.this, RxJavaDemo11Activity.class);
                startActivity(intent);
            }
        });
    }
}
