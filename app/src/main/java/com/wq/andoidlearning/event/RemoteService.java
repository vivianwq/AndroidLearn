package com.wq.andoidlearning.event;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RemoteService extends Service {

    private static final String TAG = "RemoteService";

    @Override
    public void onCreate() {
        Log.i(TAG, "MyService is onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "MyProcessActivity is created: ");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "OnDestroy");
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
