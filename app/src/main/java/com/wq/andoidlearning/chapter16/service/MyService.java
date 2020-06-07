package com.wq.andoidlearning.chapter16.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

public class MyService extends Service {

    class IService extends Binder {
        public void test() {
            EventBus.getDefault().post(new ServiceBean("MyService--Binder方法调用"+Thread.currentThread().getName()));
        }
    }

    private IService iService;

    @Override
    public void onCreate() {
        super.onCreate();
        iService = new IService();

        EventBus.getDefault().post(new ServiceBean("MyService--onCreate"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        EventBus.getDefault().post(new ServiceBean("MyService--onStartCommand--" + super.onStartCommand(intent, flags, startId)+Thread.currentThread().getName())
        );
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new ServiceBean("MyService--onDestroy"));
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        EventBus.getDefault().post(new ServiceBean("MyService--unbindService"));
    }


    @Override
    public boolean stopService(Intent name) {
        EventBus.getDefault().post(new ServiceBean("MyService--stopService"));
        return super.stopService(name);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        EventBus.getDefault().post(new ServiceBean("MyService--onBind"));
        return iService;
    }
}
