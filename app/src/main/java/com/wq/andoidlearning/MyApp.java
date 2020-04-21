package com.wq.andoidlearning;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wq.lib_base.util.Utils;

public class MyApp extends Application {

    public static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (Utils.isApkInDebug(myApp)) {
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
            //线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
