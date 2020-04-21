package com.wq.andoidlearning.arouter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wq.andoidlearning.R;
import com.wq.lib_base.RouterMap;
import com.wq.lib_base.util.FragmentUtils;

public class ARouterMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter_main);
        addFragment();

    }

    private void addFragment() {
        Fragment homeFragment = getHomeFragment();
        FragmentUtils.addFragment(this, homeFragment, R.id.fl_container);
    }

    private Fragment getHomeFragment() {
        return (Fragment) ARouter
                .getInstance()
                .build(RouterMap.HOME_FRAGMENT)
                .navigation();
    }
}
