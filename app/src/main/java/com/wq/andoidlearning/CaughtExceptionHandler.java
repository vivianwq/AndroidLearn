package com.wq.andoidlearning;

import android.util.Log;

public class CaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static CaughtExceptionHandler caughtExceptionHandler = null;

    private CaughtExceptionHandler() {

    }

    public static CaughtExceptionHandler getInstance() {

        if (caughtExceptionHandler == null) {

            synchronized (CaughtExceptionHandler.class) {

                if (caughtExceptionHandler == null) {
                    caughtExceptionHandler = new CaughtExceptionHandler();
                }
            }
        }

        return caughtExceptionHandler;
    }

    public void setDefaultUnCaughtExceptionHandler() {
        //在Application开始时调用
        Thread.setDefaultUncaughtExceptionHandler(this);
        //设置应用默认的全局捕获异常器
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.d("CaughtExceptionHandler", e.getMessage());
        //异常信息
        //可以上传错误日志
        android.os.Process.killProcess(android.os.Process.myPid());
        //关闭虚拟机，释放所有内存
        System.exit(0);
    }
}
