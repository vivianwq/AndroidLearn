package com.wq.module_other;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wq.lib_base.RouterMap;
import com.wq.lib_base.service.StoreModuleRouterService;

@Route(path = RouterMap.INTER_MIDDLE_ACTIVITY)
public class InterMiddleActivity extends AppCompatActivity {

    private Switch switchButton;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_middle);
        tvStatus = findViewById(R.id.tvStatus);
        switchButton = findViewById(R.id.switchButton);
        updateStatus();
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                StoreModuleRouterService.setLogin(isChecked);
                updateStatus();
            }
        });

    }

    private void updateStatus() {
        tvStatus.setText("登录状态=" + StoreModuleRouterService.isLogin());
    }
}
