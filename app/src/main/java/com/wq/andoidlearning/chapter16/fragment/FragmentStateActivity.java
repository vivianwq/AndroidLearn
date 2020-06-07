package com.wq.andoidlearning.chapter16.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.chapter16.service.ItemFragment;
import com.wq.andoidlearning.chapter16.service.Vp1Adapter;
import com.wq.andoidlearning.chapter16.service.Vp2Adapter;
import com.wq.andoidlearning.chapter16.service.dummy.DummyContent;
import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class FragmentStateActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private ViewPager vp1, vp2;
    private List<Fragment> itemFragmentList;
    private List<Fragment> itemFragmentList2;
    private Vp1Adapter vp1Adapter;
    private TextView tvContent;
    private StringBuilder stringBuilder = new StringBuilder();
    private Vp2Adapter vp2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_state);
        tvContent = findViewById(R.id.tvContent);
        tvContent.invalidate();
        EventBus.getDefault().register(this);
        itemFragmentList = new ArrayList<>();
        itemFragmentList2 = new ArrayList<>();
        itemFragmentList.add(ItemFragment.newInstance(5,"1"));
        itemFragmentList.add(ItemFragment.newInstance(4,"1"));
        itemFragmentList.add(ItemFragment.newInstance(3,"1"));

        itemFragmentList2.add(ItemFragment.newInstance(5,"2"));
        itemFragmentList2.add(ItemFragment.newInstance(4,"2"));
        itemFragmentList2.add(ItemFragment.newInstance(3,"2"));
        vp1 = findViewById(R.id.vp1);
        vp2 = findViewById(R.id.vp2);
        vp1Adapter = new Vp1Adapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, itemFragmentList);
        vp2Adapter = new Vp2Adapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, itemFragmentList2);
        vp1.setAdapter(vp1Adapter);
        vp2.setAdapter(vp2Adapter);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = ItemFragment.newInstance(1,"1");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Subscriber()
    public void showText(ServiceBean serviceBean) {
        stringBuilder.append(serviceBean.getMsg() + "\n");
        tvContent.setText(stringBuilder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
