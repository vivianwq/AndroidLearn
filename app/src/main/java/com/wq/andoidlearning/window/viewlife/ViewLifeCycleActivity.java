package com.wq.andoidlearning.window.viewlife;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class ViewLifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_life_cycle);
        Log.i("ViewLifeCycleActivity","onCreate");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ViewLifeCycleActivity","onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ViewLifeCycleActivity","onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ViewLifeCycleActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ViewLifeCycleActivity","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ViewLifeCycleActivity","onDestroy");
    }
}
