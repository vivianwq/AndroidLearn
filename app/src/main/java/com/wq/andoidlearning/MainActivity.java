package com.wq.andoidlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.dagger.DaggerMainActivity;
import com.wq.andoidlearning.materialdesign.MainMaterialActivity;
import com.wq.andoidlearning.pattern.PatternMainActivity;
import com.wq.andoidlearning.rxjava.RxJavaDemoMainActivity;
import com.wq.andoidlearning.status.StatusBarActivity1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarActivity1.setStatusBarTransparent(MainActivity.this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStatusBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatusBarActivity1.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btnBehavior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainMaterialActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnDagger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DaggerMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnRxJava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxJavaDemoMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnPattern).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatternMainActivity.class);
                startActivity(intent);
            }
        });

    }
}
