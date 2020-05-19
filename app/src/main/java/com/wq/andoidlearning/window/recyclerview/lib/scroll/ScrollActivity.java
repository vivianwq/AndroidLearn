package com.wq.andoidlearning.window.recyclerview.lib.scroll;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.window.recyclerview.lib.data.DataProvider;
import com.wq.andoidlearning.window.recyclerview.lib.scroll.inter.OnPagerListener;
import com.wq.andoidlearning.window.recyclerview.lib.scroll.recycler.PagerLayoutManager;

import org.yczbj.ycrefreshviewlib.utils.RefreshLogUtils;

public class ScrollActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
    }

    private void initRecyclerView() {
        PagerLayoutManager viewPagerLayoutManager = new PagerLayoutManager(
                this, OrientationHelper.VERTICAL);
        ScrollAdapter adapter = new ScrollAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(viewPagerLayoutManager);
        viewPagerLayoutManager.setOnViewPagerListener(new OnPagerListener() {
            @Override
            public void onInitComplete() {
                System.out.println("OnPagerListener---onInitComplete--"+"初始化完成");
                RefreshLogUtils.d("OnPagerListener---onInitComplete--"+"初始化完成");
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                System.out.println("OnPagerListener---onPageRelease--"+position+"-----"+isNext);
                RefreshLogUtils.d("OnPagerListener---onPageRelease--"+position+"-----"+isNext);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                System.out.println("OnPagerListener---onPageSelected--"+position+"-----"+isBottom);
                RefreshLogUtils.d("OnPagerListener---onPageSelected--"+position+"-----"+isBottom);
            }
        });
        adapter.setData(DataProvider.getList(30));
    }



}
