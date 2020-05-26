package com.wq.andoidlearning.component.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.improve.loop.LogUtils;

public class BActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        LogUtils.i("BActivity--onCreate");
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("BActivity--onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("BActivity--onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("BActivity--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("BActivity--onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("BActivity--onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("BActivity--onDestroy");
    }



    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        tvContent.setText("目标值");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            tvContent.setText("ORIENTATION_LANDSCAPE");
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            tvContent.setText("ORIENTATION_PORTRAIT");
    }
}
