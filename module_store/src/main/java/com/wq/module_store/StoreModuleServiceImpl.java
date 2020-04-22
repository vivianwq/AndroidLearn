package com.wq.module_store;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wq.lib_base.RouterMap;
import com.wq.lib_base.service.IStoreModuleService;

@Route(path = RouterMap.STORE_MODULE_SERVICE)
public class StoreModuleServiceImpl implements IStoreModuleService {

    public static final String FILE = "account";
    public static final String IS_LOGIN = "is_login";
    private Context context;


    @Override
    public boolean isLogin() {
        return context.getSharedPreferences(FILE,
                Context.MODE_PRIVATE)
                .getBoolean(IS_LOGIN, false);
    }

    @Override
    public void setLogin(boolean login) {
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE).edit().putBoolean(IS_LOGIN, login).apply();
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
