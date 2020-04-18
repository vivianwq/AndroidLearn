package com.wq.andoidlearning.materialdesign.tablayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.wq.andoidlearning.R;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<String> list;

    public ViewPagerAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup itemView = (ViewGroup) LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_view_pager, container, false);
        container.addView(itemView);
        TextView tv = itemView.findViewById(R.id.tv);
        tv.setText(list.get(position));
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
