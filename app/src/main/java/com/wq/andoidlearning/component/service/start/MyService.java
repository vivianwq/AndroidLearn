package com.wq.andoidlearning.component.service.start;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.wq.andoidlearning.component.service.ServiceBean;
import com.wq.andoidlearning.component.service.bind.IBinderWorker;

import org.simple.eventbus.EventBus;

public class MyService extends Service {

    public class MyBinder extends Binder implements IBinderWorker {

        @Override
        public String showMsg(String msg) {
            String msgStr = msg + "通过BinderService的IBinder对象--发送前";
            EventBus.getDefault().post(new ServiceBean(msgStr));
            return msgStr;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().post(new ServiceBean("MyService--onCreate"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String content = "";
        if (intent != null) {
            content = intent.getStringExtra("service");
        }
        EventBus.getDefault().post(new ServiceBean("MyService--onStartCommand--intent--" + content));
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        EventBus.getDefault().post(new ServiceBean("MyService--onBind"));
        return new MyBinder();
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().post(new ServiceBean("MyService--onDestroy"));
        super.onDestroy();
    }
}
