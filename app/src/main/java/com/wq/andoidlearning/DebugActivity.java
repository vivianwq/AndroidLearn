package com.wq.andoidlearning;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DebugActivity extends AppCompatActivity {

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i=100;
        setContentView(R.layout.activity_debug);

        for (int i = 0; i < 10; i++) {
            //获取当前i的值
            int selector = i;
            Log.i("DebugActivity", "for当前的i的值:" + i);
            stepNext(selector);
        }
    }

    private void stepNext(int i) {
        Log.i("DebugActivity", "stepNext当前的i的值" + i);
    }
}
