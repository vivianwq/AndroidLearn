package com.wq.andoidlearning.improve.leak;

import android.content.Context;

public class LoginManager {
    private static LoginManager mInstance;
    private Context mContext;

    private LoginManager(Context context) {
        this.mContext = context;
        //修改代码：this.mContext = context.getApplicationContext();
    }

    public static LoginManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LoginManager.class) {
                if (mInstance == null) {
                    mInstance = new LoginManager(context);
                }
            }
        }
        return mInstance;
    }

    public void dealData() {
    }
}
