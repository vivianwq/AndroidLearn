package com.wq.andoidlearning.window.recyclerview.lib.scroll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.window.recyclerview.lib.data.DataProvider;
import com.wq.andoidlearning.window.recyclerview.lib.data.PersonData;
import com.wq.andoidlearning.window.recyclerview.lib.scroll.inter.OnPagerListener;
import com.wq.andoidlearning.window.recyclerview.lib.scroll.pager.AbsPagerAdapter;
import com.wq.andoidlearning.window.recyclerview.lib.scroll.pager.LayoutViewPager;

import java.util.List;

public class ScrollPagerActivity extends AppCompatActivity {

    private LayoutViewPager vp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        vp = findViewById(R.id.vp);
        initViewPager();
    }

    private void initViewPager() {
        List<PersonData> list = DataProvider.getList(30);
        vp.setOffscreenPageLimit(1);
        vp.setCurrentItem(0);
        BannerPagerAdapter adapter = new BannerPagerAdapter(list);
        vp.setAdapter(adapter);
        vp.setAnimationDuration(1000);
        vp.setOnViewPagerListener(new OnPagerListener() {
            @Override
            public void onInitComplete() {

            }

            @Override
            public void onPageRelease(boolean isNext, int position) {

            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {

            }
        });
    }

    class BannerPagerAdapter extends AbsPagerAdapter {

        private List<PersonData> data;

        public BannerPagerAdapter(List<PersonData> dataList) {
            super(dataList);
            this.data = dataList;
        }


        @Override
        public View getView(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(
                    R.layout.item_image_pager, container, false);
            ImageView imageView = view.findViewById(R.id.iv_image);
            imageView.setImageResource(data.get(position).getImage());
            return view;
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }
}
