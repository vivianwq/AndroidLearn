package com.wq.andoidlearning.component.broadcastreceiver.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

public class MyBroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        if (resultData != null)
            EventBus.getDefault().post(new ServiceBean("MyBroadcastReceiver2接收到消息--" + resultData));
        else {
            EventBus.getDefault().post(new ServiceBean("MyBroadcastReceiver2接收到消息"));
        }
        abortBroadcast();

    }
}
