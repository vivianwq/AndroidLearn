package com.wq.andoidlearning.chapter16;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class Main2Activity extends AppCompatActivity {

    private StringBuilder stringBuilder = new StringBuilder();
    private TextView tvContent;

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getString("save") != null) {
            stringBuilder.append("onRestoreInstanceState" + savedInstanceState.getString("save")
                    + "\n");
            tvContent.setText(stringBuilder.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvContent = findViewById(R.id.tvContent);
        if (savedInstanceState != null && savedInstanceState.getString("save") != null) {
            stringBuilder.append("onCreate--saveD---" + savedInstanceState.getString("save")
                    + "\n");
        } else {
            stringBuilder.append("onCreate" + "\n");
        }
        tvContent.setText(stringBuilder.toString());
        findViewById(R.id.btnPortrait).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SourceLockedOrientationActivity")
            @Override
            public void onClick(View v) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
        findViewById(R.id.btnLandScape).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SourceLockedOrientationActivity")
            @Override
            public void onClick(View v) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        stringBuilder.append("onStart" + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        stringBuilder.append("onRestart" + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        stringBuilder.append("onResume" + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        stringBuilder.append("onStop" + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        stringBuilder.append("onPause" + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("save", "保存状态字符串");
        stringBuilder.append("onSaveInstanceState" + "---"
                + "保存状态字符串" + "---" + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String str = "未知方向";
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            str = "竖屏";
        }
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            str = "横屏";
        }
        stringBuilder.append("onConfigurationChanged" + "---"
                + str + "---" + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stringBuilder.append("onDestroy" + "\n");
        stringBuilder.append("----页面被销毁,即将重建----" + "\n");
        stringBuilder.append("----Activity销毁时页面内所有对象也会被重新建立----" + "\n");
        tvContent.setText(stringBuilder.toString());
    }
}
