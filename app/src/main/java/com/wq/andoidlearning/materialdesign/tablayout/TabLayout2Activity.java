package com.wq.andoidlearning.materialdesign.tablayout;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;
import com.wq.andoidlearning.R;

import java.lang.reflect.Field;

public class TabLayout2Activity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout2);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));

        findViewById(R.id.btnAddDivider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
                linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                linearLayout.setDividerDrawable(ContextCompat.getDrawable(TabLayout2Activity.this,
                        R.drawable.divider));
            }
        });


        findViewById(R.id.btnChangeLength).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int left = 20;//单位dp
                int right = 20;//单位dp
                Class<? extends TabLayout> aClass = tabLayout.getClass();
                Field slidingTabIndicator = null;
                try {
                    slidingTabIndicator = aClass.getDeclaredField
                            ("slidingTabIndicator");
                    slidingTabIndicator.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                LinearLayout llTab = null;
                try {
                    llTab = (LinearLayout) slidingTabIndicator.get(tabLayout);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int l = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, left, Resources.getSystem().getDisplayMetrics());
                int r = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, right, Resources.getSystem().getDisplayMetrics());

                if (llTab != null) {
                    for (int i = 0; i < llTab.getChildCount(); i++) {
                        View childAt = llTab.getChildAt(i);
                        childAt.setPadding(0, 0, 0, 0);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                        params.leftMargin = l;
                        params.rightMargin = r;
                        childAt.setLayoutParams(params);
                        childAt.invalidate();
                    }
                }
            }
        });
    }
}
