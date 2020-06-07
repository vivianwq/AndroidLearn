package com.wq.andoidlearning.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class ListViewActivity extends AppCompatActivity {

    private ListView lv;
    private ListMyAdapter listMyAdapter;
    public static final int TAG = 2131492949;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int activity_list_view = R.layout.activity_list_view;
        Log.i("ListViewActivity", "当前id" + activity_list_view);
        Log.i("ListViewActivity", "展示final" + TAG);
//        setContentView(R.layout.activity_list_view);
        setContentView(activity_list_view);
        lv = findViewById(R.id.lv);
        listMyAdapter = new ListMyAdapter(ListViewActivity.this);
        lv.setAdapter(listMyAdapter);
    }
}
