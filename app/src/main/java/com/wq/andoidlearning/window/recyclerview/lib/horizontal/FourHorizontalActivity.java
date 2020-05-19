package com.wq.andoidlearning.window.recyclerview.lib.horizontal;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.window.recyclerview.lib.data.AppUtils;
import com.wq.andoidlearning.window.recyclerview.lib.data.DataProvider;

import org.yczbj.ycrefreshviewlib.inter.OnLoadMoreListener;
import org.yczbj.ycrefreshviewlib.item.SpaceViewItemLine;
import org.yczbj.ycrefreshviewlib.view.YCRefreshView;


public class FourHorizontalActivity extends AppCompatActivity {


    private YCRefreshView recyclerView;
    private NarrowImageAdapter adapter;
    private Handler handler = new Handler();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler!=null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter = new NarrowImageAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new SpaceViewItemLine((int)
                AppUtils.convertDpToPixel(8,this)));
        adapter.setMore(R.layout.view_more_horizontal, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(DataProvider.getNarrowImage(0));
                    }
                },1000);
            }
        });
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                adapter.addAll(DataProvider.getNarrowImage(0));
            }
        });
        adapter.addAll(DataProvider.getNarrowImage(0));
    }

}
