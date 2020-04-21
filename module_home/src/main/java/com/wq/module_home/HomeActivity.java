package com.wq.module_home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.lib_base.util.FragmentUtils;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
    }

    private void addFragment() {
        FragmentUtils
                .addFragment(this, new HomeFragment(),
                        R.id.fl_container);
    }
}
