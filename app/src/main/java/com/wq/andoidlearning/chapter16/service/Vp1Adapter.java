package com.wq.andoidlearning.chapter16.service;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

import java.util.List;

public class Vp1Adapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public Vp1Adapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        EventBus.getDefault().post(new ServiceBean("FragmentPagerAdapter--instantiateItem"));
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        EventBus.getDefault().post(new ServiceBean("FragmentPagerAdapter--destroyItem"));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
