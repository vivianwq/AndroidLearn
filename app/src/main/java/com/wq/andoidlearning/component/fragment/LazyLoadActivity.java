package com.wq.andoidlearning.component.fragment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class LazyLoadActivity extends AppCompatActivity {

    private ViewPager vp;
    private MyViewPagerAdapter myViewPagerAdapter;

    private Fragment1 fragment1 = Fragment1.newInstance();
    private Fragment2 fragment2 = Fragment2.newInstance();
    private Fragment3 fragment3 = Fragment3.newInstance();
    private Fragment4 fragment4 = Fragment4.newInstance();
    private List<Fragment> list;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_load);
        EventBus.getDefault().register(this);
        vp = findViewById(R.id.vp);
        vp.setOffscreenPageLimit(2);
        tvContent = findViewById(R.id.tvContent);
        stringBuilder=new StringBuilder();
        list = new ArrayList<>();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        myViewPagerAdapter = new MyViewPagerAdapter(list, getSupportFragmentManager());
        vp.setAdapter(myViewPagerAdapter);


    }
    private StringBuilder stringBuilder;

    @Subscriber
    public void onShowText(ServiceBean serviceBean) {
        stringBuilder.append(serviceBean.getMsg());
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}


