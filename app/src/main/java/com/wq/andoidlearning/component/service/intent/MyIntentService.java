package com.wq.andoidlearning.component.service.intent;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        this("MyIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().post(new ServiceBean("MyIntentService--onCreate"));
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        EventBus.getDefault().post(new ServiceBean("MyIntentService--onStartCommand"));
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        EventBus.getDefault().post(new ServiceBean("MyIntentService--onHandleIntent--" + Thread.currentThread().getName()
                + "是否主线程?" + (Thread.currentThread() == Looper.getMainLooper().getThread()))
        );
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new ServiceBean("MyIntentService--onDestroy"));

    }


}
