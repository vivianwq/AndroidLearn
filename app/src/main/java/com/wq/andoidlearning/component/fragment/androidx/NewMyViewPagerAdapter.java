package com.wq.andoidlearning.component.fragment.androidx;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class NewMyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public NewMyViewPagerAdapter(List<Fragment> list, FragmentManager fragmentManager) {
        super(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }


    @Override
    public int getCount() {
        return list.size();
    }


}
