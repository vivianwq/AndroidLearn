package com.wq.lib_base;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

@Interceptor(priority = 1, name = "重新分组进行拦截")
public class BaseInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        //getExtra()对应目标Activity中的@Route声明
        if (postcard.getExtra() == ConstantMap.LOGIN_EXTRA) {
            boolean isLogin = postcard.getExtras()
                    .getBoolean(ConstantMap.IS_LOGIN);
            if (!isLogin) {
                //如果没有登录,那么跳转到登录界面
                ARouter.getInstance()
                        .build(RouterMap.INTER_MIDDLE_ACTIVITY)
                        .navigation();
            } else {
                //否则继续放行
                postcard.withString(ConstantMap.IS_LOGIN_EXTRA, "登录了!");
                callback.onContinue(postcard);
            }
        } else {
            //对于其他不需要登录的界面直接放行
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}
