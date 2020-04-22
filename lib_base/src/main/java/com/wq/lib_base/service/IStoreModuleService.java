package com.wq.lib_base.service;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IStoreModuleService extends IProvider {

    //是否登录
    boolean isLogin();

    //设置是否登录
    void setLogin(boolean login);
}
