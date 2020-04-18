package com.wq.andoidlearning.materialdesign.bottom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.wq.andoidlearning.R;

public class BottomBarActivity extends AppCompatActivity {

    private BottomNavigationBar bottomBar;
    private BottomNavigationItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        initView();
        addBadge();


    }

    public void show(View view) {
        bottomBar.show();
    }

    public void hide(View view) {
        bottomBar.hide();
    }

    public void addBadge() {
        findViewById(R.id.btnAddBadge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextBadgeItem badgeItem = new TextBadgeItem()
                        //设置角标背景色
                        .setBackgroundColorResource(android.R.color.holo_red_dark)
                        //设置角标的文字
                        .setText("5")
                        //设置角标文字颜色
                        .setTextColorResource(android.R.color.white)
                        //在选中时是否隐藏角标
                        .setHideOnSelect(true);


                bottomBar.addItem(new BottomNavigationItem(android.R.drawable.ic_dialog_map,
                        "Item 5")
                        .setBadgeItem(badgeItem)
                        .setActiveColorResource(android.R.color.holo_blue_dark));
                //通过build模式建造
                bottomBar.initialise();


            }
        });

        bottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.i("bottomBar", "onTabSelected=" + "position=" + position);
            }

            @Override
            public void onTabUnselected(int position) {
                Log.i("bottomBar", "onTabUnselected=" + "position=" + position);

            }

            @Override
            public void onTabReselected(int position) {
                Log.i("bottomBar", "onTabReselected=" + "position=" + position);
            }
        });
    }

    private void initView() {
        bottomBar = findViewById(R.id.bottomBar);
        //1.设置Mode
        bottomBar.setMode(BottomNavigationBar.MODE_FIXED);

//        bottomBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        //2.设置BackgroundStyle
        bottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        bottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);

        //3.设置背景色
        bottomBar.setBarBackgroundColor(android.R.color.white);
        //4.设置每个item
        bottomBar.addItem(new BottomNavigationItem(android.R.drawable.ic_dialog_map,
                "Item 1").setActiveColorResource(android.R.color.holo_blue_dark));
        bottomBar.addItem(new BottomNavigationItem(android.R.drawable.ic_dialog_map,
                "Item 2").setActiveColorResource(android.R.color.holo_green_dark));
        bottomBar.addItem(new BottomNavigationItem(android.R.drawable.ic_dialog_map,
                "Item 3").setActiveColorResource(android.R.color.holo_orange_dark));
        item = new BottomNavigationItem(android.R.drawable.ic_dialog_map,
                "Item 4").setActiveColorResource(android.R.color.holo_green_dark);
        bottomBar.addItem(item);
//        bottomNavigationItem = new BottomNavigationItem(android.R.drawable.ic_dialog_map,
//                "Item 5").setActiveColorResource(android.R.color.holo_blue_dark);

        bottomBar.setFirstSelectedPosition(3);
        //5.初始化
        bottomBar.initialise();
    }
}
