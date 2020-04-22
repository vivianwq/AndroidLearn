package com.wq.module_other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wq.lib_base.ConstantMap;
import com.wq.lib_base.RouterMap;

@Route(path = RouterMap.RESULT_SERVER_ACTIVITY)
public class ResultServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_server);
        findViewById(R.id.btnFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ConstantMap.FOR_RESULT_KEY, "返回数据给Client");
                setResult(ConstantMap.FOR_RESULT_CODE, intent);
                finish();
            }
        });
    }
}
