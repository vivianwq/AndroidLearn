package com.wq.module_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wq.lib_base.ConstantMap;
import com.wq.lib_base.RouterMap;
import com.wq.lib_base.util.Utils;

public class ResultClientActivity extends AppCompatActivity {

    private Button btnResultClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_client);
        btnResultClient = findViewById(R.id.btnResultClient);
        btnResultClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter
                        .getInstance()
                        .build(RouterMap.RESULT_SERVER_ACTIVITY)
                        .navigation(ResultClientActivity.this, ConstantMap.FOR_RESULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstantMap.FOR_RESULT_CODE:
                Utils.toast(ResultClientActivity.this,
                        "receive=" + data.getStringExtra(ConstantMap.FOR_RESULT_KEY));
                break;
        }
    }
}
