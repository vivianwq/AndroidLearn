package com.wq.andoidlearning.materialdesign.nested.demo2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

public class Demo2Activity extends AppCompatActivity {

    private RecyclerView rv;
    private ElemeDetailView edv;
    private View edvTitle;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        rv = findViewById(R.id.rv);
        edv = findViewById(R.id.edvParent);
        edvTitle = findViewById(R.id.edvTitle);
        //监听edvContent的位置改变,并改变edvTitle的透明度
        edv.setListener(new ElemeDetailView.Listener() {
            @Override
            public void onContentPositionChanged(float fraction) {
                edvTitle.setAlpha(1 - fraction);
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new Adapter(this);
        rv.setAdapter(adapter);

    }
}
