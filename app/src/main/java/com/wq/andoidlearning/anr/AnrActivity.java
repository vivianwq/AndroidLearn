package com.wq.andoidlearning.anr;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.improve.loop.LogUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class AnrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    LogUtils.i("onClick of R.id.button1: " + e);
                }
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 100; ++i) {
                    readFile();
                }
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = compute();
                System.out.println(result);
            }
        });
    }

    private static double compute() {
        double result = 0;
        for (int i = 0; i < 1000000; ++i) {
            result += Math.acos(Math.cos(i));
            result -= Math.asin(Math.sin(i));
        }
        return result;
    }

    private static void readFile() {
        FileInputStream reader = null;
        try {
            reader = new FileInputStream("/proc/stat");
            while (reader.read() != -1) ;
        } catch (IOException e) {
            LogUtils.i("readFile: /proc/stat" + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LogUtils.i(" on close reader " + e);
                }
            }
        }
    }
}
