package com.wq.andoidlearning;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ActivityRefWatcher {
    private MyApp myApp;

    public ActivityRefWatcher(MyApp myApp) {
        this.myApp = myApp;
    }

    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks =
            new Application.ActivityLifecycleCallbacks() {

                @Override
                public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                    Log.i("ActivityRefWatcher", activity.getComponentName() + "onActivityCreated");

                }

                @Override
                public void onActivityStarted(@NonNull Activity activity) {
                    Log.i("ActivityRefWatcher", activity.getComponentName() + "onActivityStarted");

                }

                @Override
                public void onActivityResumed(@NonNull Activity activity) {
                    Log.i("ActivityRefWatcher", activity.getComponentName() + "onActivityResumed");

                }

                @Override
                public void onActivityPaused(@NonNull Activity activity) {
                    Log.i("ActivityRefWatcher", activity.getComponentName() + "onActivityPaused");

                }

                @Override
                public void onActivityStopped(@NonNull Activity activity) {
                    Log.i("ActivityRefWatcher", activity.getComponentName() + "onActivityStopped");

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    Log.i("ActivityRefWatcher", activity.getComponentName() + "onActivitySaveInstanceState");
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    Log.i("ActivityRefWatcher", activity.getComponentName() + "onActivityDestroyed");
                }
            };

    public void watchActivities() {
        // Make sure you don’t get installed twice.
//        stopWatchingActivities();
//        myApp.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public void stopWatchingActivities() {
//        myApp.unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
    }
}
