package com.wq.andoidlearning.materialdesign.recycler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeLoadLayout swipeLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        swipeLoadLayout = findViewById(R.id.swipe_load_layout);
        recyclerView = findViewById(R.id.recyclerView);

        String[] items = getResources().getStringArray(R.array.tab_B);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);

        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this,
                RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
