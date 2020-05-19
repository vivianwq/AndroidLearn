package com.wq.andoidlearning.window.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.window.recyclerview.verticalrecyclerview.VerticalViewActivity;

import java.util.ArrayList;

public class RecyclerViewMainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MultipleItemAdapter multipleItemAdapter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        findViewById(R.id.btnMyView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerViewMainActivity.this,
                        VerticalViewActivity.class));
            }
        });

        findViewById(R.id.btnLinear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager manager = new LinearLayoutManager(RecyclerViewMainActivity.this);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setOnFlingListener(null);
                LinearSnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(recyclerView);
                SnapAdapter adapter = new SnapAdapter(RecyclerViewMainActivity.this);
                recyclerView.setAdapter(adapter);
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    list.add("第" + i + "条数据");
                }
                adapter.addAll(list);
                adapter.notifyDataSetChanged();


            }
        });

        findViewById(R.id.btnPager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager manager = new LinearLayoutManager(RecyclerViewMainActivity.this);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setOnFlingListener(null);
                PagerSnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(recyclerView);
                SnapAdapter adapter = new SnapAdapter(RecyclerViewMainActivity.this);
                recyclerView.setAdapter(adapter);
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    list.add("第" + i + "条数据");
                }
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.btnLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LinearLayoutManager layoutManager = new LinearLayoutManager(RecyclerViewMainActivity.this);
                //设置layoutManager
                recyclerView.setLayoutManager(layoutManager);
                final RecycleViewItemLine line = new RecycleViewItemLine(RecyclerViewMainActivity.this, LinearLayout.HORIZONTAL, 1,
                        RecyclerViewMainActivity.this.getResources().getColor(R.color.colorAccent));
                //设置添加分割线
                recyclerView.addItemDecoration(line);
                multipleItemAdapter = new MultipleItemAdapter(RecyclerViewMainActivity.this);
                //设置adapter
                recyclerView.setAdapter(multipleItemAdapter);
                //添加数据并且刷新adapter
                for (int i = 0; i < 50; i++) {
                    list.add("第" + i + "条数据");
                }
                multipleItemAdapter.addAll(list);
                multipleItemAdapter.notifyDataSetChanged();


                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    private boolean isSlidingUpward = false;

                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            int lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                            int itemCount = layoutManager.getItemCount();
                            // 判断是否滑动到了最后一个item，并且是向上滑动
                            if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ArrayList<String> list = new ArrayList<>();
                                        for (int i = 0; i < 50; i++) {
                                            list.add("新增第" + i + "条数据");
                                        }
                                        updateList(list, true);
                                        Toast.makeText(RecyclerViewMainActivity.this,
                                                "用户上拉列表添加数据", Toast.LENGTH_SHORT).show();
                                    }
                                }, 2000);
                            }
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        isSlidingUpward = dy > 0;
                    }
                });
            }
        });
        findViewById(R.id.btnGrid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<SpanModel> list = new ArrayList<>();
                GridLayoutManager layoutManager = new GridLayoutManager(RecyclerViewMainActivity.this, 6);
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        SpanModel model = list.get(position);
                        if (model.getType() == 1) {
                            return 6;
                        } else if (model.getType() == 2) {
                            return 3;
                        } else if (model.getType() == 3) {
                            return 2;
                        } else if (model.getType() == 4) {
                            return 2;
                        } else {
                            return 1;
                        }
                    }
                });
                //设置layoutManager
                recyclerView.setLayoutManager(layoutManager);
                final RecycleViewItemLine line = new RecycleViewItemLine(RecyclerViewMainActivity.this, LinearLayout.HORIZONTAL, 1,
                        RecyclerViewMainActivity.this.getResources().getColor(R.color.colorAccent));
                //设置添加分割线
                recyclerView.addItemDecoration(line);
                GridItemAdapter gridItemAdapter = new GridItemAdapter(RecyclerViewMainActivity.this);
                //设置adapter
                recyclerView.setAdapter(gridItemAdapter);
                //添加数据并且刷新adapter
                for (int i = 0; i < 1; i++) {
                    SpanModel spanModel = new SpanModel();
                    spanModel.setData("第" + i + "条数据");
                    spanModel.setType(1);
                    list.add(spanModel);
                }
                for (int i = 0; i < 8; i++) {
                    SpanModel spanModel = new SpanModel();
                    spanModel.setData("第" + i + "条数据");
                    spanModel.setType(2);
                    list.add(spanModel);
                }
                for (int i = 0; i < 6; i++) {
                    SpanModel spanModel = new SpanModel();
                    spanModel.setData("第" + i + "条数据");
                    spanModel.setType(3);
                    list.add(spanModel);
                }

                for (int i = 0; i < 12; i++) {
                    SpanModel spanModel = new SpanModel();
                    spanModel.setData("第" + i + "条数据");
                    spanModel.setType(6);
                    list.add(spanModel);
                }
                gridItemAdapter.addAll(list);
                gridItemAdapter.notifyDataSetChanged();
            }
        });

    }


    /**
     * 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
     */
    public void updateList(ArrayList<String> newData, boolean hasMore) {
        int size = list.size();
        // 在原有的数据之上增加新数据
        if (newData != null) {
            list.addAll(newData);
//            this.hasMore = hasMore;
//            multipleItemAdapter.notifyItemRangeInserted(size, newData.size());
            multipleItemAdapter.addAll(newData);
            multipleItemAdapter.notifyDataSetChanged();
        }
    }
}
