package com.wq.andoidlearning.component.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wq.andoidlearning.R;

public class Fragment1 extends BaseLazyLoadFragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView lv;
    private Handler handler = new Handler();

    public Fragment1() {
    }


    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.layout_fragment1;
    }

    @Override
    protected void init() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        lv = (ListView) findViewById(R.id.lv);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    protected void loadData() {
        onRefresh();

    }


    @Override
    public void onRefresh() {
        // 只加载一次数据，避免界面切换的时候，加载数据多次
        if (lv.getAdapter() == null) {
            swipeRefreshLayout.setRefreshing(true);
            new Thread() {
                @Override
                public void run() {
                    // 延迟1秒
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            lv.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, new String[]{
                                    "111", "111", "111", "111", "111", "111", "111", "111", "111", "111", "111",
                                    "111", "111", "111", "111", "111", "111", "111", "111", "111", "111", "111"
                            }));
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }.start();
        }
    }
}
