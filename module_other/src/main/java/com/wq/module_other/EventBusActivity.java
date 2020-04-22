package com.wq.module_other;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wq.lib_base.ConstantMap;
import com.wq.lib_base.RouterMap;

import org.simple.eventbus.EventBus;

@Route(path = RouterMap.EVENT_BUS_ACTIVITY)
public class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        final int revValue = getIntent().getIntExtra(ConstantMap.EVENT_BUS_DATA, 0);
        findViewById(R.id.btnEventBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus
                        .getDefault()
                        .post("通过EventBus返回数据="
                        +revValue
                        ,ConstantMap.EVENT_BUS_KEY);
                finish();
            }
        });
    }
}
