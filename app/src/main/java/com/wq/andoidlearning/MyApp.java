package com.wq.andoidlearning;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.moduth.blockcanary.BlockCanary;
import com.wq.lib_base.util.Utils;

public class MyApp extends Application {

    public static MyApp myApp;


    private static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        Log.i("MyContentProvider--2", "onCreate"+this);
        //        在application的onCreate方法前

        ActivityRefWatcher activityRefWatcher = new ActivityRefWatcher(this);
        activityRefWatcher.watchActivities();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        //penaltyLog表示是否打印日志。
        super.onCreate();
        //应用开始就设置全局捕获异常器没有设置就会用系统默认的
        CaughtExceptionHandler.getInstance().setDefaultUnCaughtExceptionHandler();
        myApp = this;
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (Utils.isApkInDebug(myApp)) {
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
            //线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
            // 在主进程初始化调用哈
            BlockCanary.install(this, new AppBlockCanaryContext()).start();
        }
        MultiDex.install(this);
        ARouter.init(this);

        int pid = android.os.Process.myPid();
        Log.i(TAG, "MyApplication is onCreate====="+"pid="+pid);
    }
}
