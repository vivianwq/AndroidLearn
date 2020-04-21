package com.wq.module_other;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wq.lib_base.ConstantMap;
import com.wq.lib_base.RouterMap;
import com.wq.lib_base.bean.SerialBean;
import com.wq.lib_base.util.Utils;

@Route(path = RouterMap.INJECT_ACTIVITY)
public class InjectActivity extends AppCompatActivity {

    @Autowired(name = ConstantMap.INJECT_AGE)
    int age;

    @Autowired(name = ConstantMap.INJECT_OBJECT)
    SerialBean serialBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ARouter.getInstance().inject(this);
        Utils.toast(this,
                "age=" + age
                        + ",bean.age="
                        + serialBean.getAge()
                        + ",bean.name="
                        + serialBean.getName());
    }
}
