package com.wq.andoidlearning.component.broadcastreceiver.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

public class MyBroadcastReceiver3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive--收到了");
        EventBus.getDefault().post(new ServiceBean("MyBroadcastReceiver3接收到消息"));
        setResultData("我想传递数据给下一个接收者");
    }
}
