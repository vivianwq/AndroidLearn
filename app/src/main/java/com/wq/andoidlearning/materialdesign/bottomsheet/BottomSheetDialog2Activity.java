package com.wq.andoidlearning.materialdesign.bottomsheet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.wq.andoidlearning.R;
import com.wq.andoidlearning.materialdesign.nested.demo2.Adapter;

public class BottomSheetDialog2Activity extends AppCompatActivity {


    private RecyclerView rv;
    private Adapter adapter;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog2);
        adapter = new Adapter(this);

        rv = findViewById(R.id.rv);
        //1.通过id获得底部菜单布局的实例
        //2.把这个底部菜单和一个BottomSheetBehavior关联起来
        mBottomSheetBehavior = BottomSheetBehavior.from(rv);
        rv.setLayoutManager(new LinearLayoutManager(BottomSheetDialog2Activity.this,
                RecyclerView.VERTICAL, false));
        rv.setAdapter(adapter);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Log.d("BottomSheet", "newState=" + i);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
//                Log.d("BottomSheet", "onSlide=" + v);
            }
        });
    }

    public void expandBottomSheet(View view) {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void hideBottomSheet(View view) {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public void collapseBottomSheet(View view) {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}
