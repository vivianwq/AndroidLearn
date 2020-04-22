package com.wq.module_other;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wq.lib_base.ConstantMap;
import com.wq.lib_base.RouterMap;
import com.wq.lib_base.service.StoreModuleRouterService;
import com.wq.lib_base.util.Utils;

@Route(path = RouterMap.INTER_TARGET_ACTIVITY, extras = ConstantMap.LOGIN_EXTRA)
public class InterTargetActivity extends AppCompatActivity {


    @Autowired(name = ConstantMap.IS_LOGIN)
    boolean status;

    @Autowired(name = ConstantMap.IS_LOGIN_EXTRA)
    String string;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_target);
        ARouter.getInstance().inject(this);
        Utils.toast(this,
                "status=" + status + "拦截字符=" + string);
        findViewById(R.id.btnLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreModuleRouterService.setLogin(false);
                finish();
            }
        });
    }
}
