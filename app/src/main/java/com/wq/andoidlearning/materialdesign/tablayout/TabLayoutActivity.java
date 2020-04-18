package com.wq.andoidlearning.materialdesign.tablayout;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wq.andoidlearning.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private static final String[] TITLE_SHORT = new String[]{
            "深圳", "南京", "内蒙古"
    };

    public static final String[] TITLE_LONG = new String[]{
            "深圳", "南京", "内蒙古呼和浩特", "广西壮族自治区", "上海", "北京", "天津"
    };

    private ViewPager vp;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        initViewPager();
        initTabLayout();
    }

    private void initViewPager() {
        vp = findViewById(R.id.vp);
        List<String> titles = new ArrayList<>();
        Collections.addAll(titles, TITLE_LONG);
        viewPagerAdapter = new ViewPagerAdapter(titles);
        vp.setAdapter(viewPagerAdapter);
    }

    private void initTabLayout() {
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("initTabLayout", "onTabSelected=position=" + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("initTabLayout", "onTabUnselected=position=" + tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("initTabLayout", "onTabReselected=position=" + tab.getPosition());

            }
        });
    }
}
