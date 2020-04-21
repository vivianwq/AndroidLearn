package com.wq.module_other;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wq.lib_base.RouterMap;
import com.wq.lib_base.util.Utils;


@Route(path = RouterMap.NO_RESULT_ACTIVITY)
public class NoResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_no_result);
        Utils.toast(this, "NoResultActivity onCreate");
    }
}
