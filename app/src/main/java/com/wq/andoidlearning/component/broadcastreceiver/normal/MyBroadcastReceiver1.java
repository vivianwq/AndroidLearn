package com.wq.andoidlearning.component.broadcastreceiver.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

public class MyBroadcastReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        EventBus.getDefault().post(new ServiceBean("MyBroadcastReceiver1接收到消息"));
    }
}
