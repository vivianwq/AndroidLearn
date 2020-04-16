package com.wq.andoidlearning.materialdesign.behavior.demo1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.materialdesign.nested.demo2.Adapter;

public class Behavior1Activity extends AppCompatActivity {

    private RecyclerView rv;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior1);

        rv=findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new Adapter(this);
        rv.setAdapter(adapter);
    }
}
