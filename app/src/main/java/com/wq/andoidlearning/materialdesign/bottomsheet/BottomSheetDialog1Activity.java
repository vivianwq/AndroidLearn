package com.wq.andoidlearning.materialdesign.bottomsheet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.wq.andoidlearning.R;

public class BottomSheetDialog1Activity extends AppCompatActivity {

    private View mBottomLayout;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog1);

        //1.通过id获得底部菜单布局的实例
        mBottomLayout = findViewById(R.id.bottom_sheet);
        //2.把这个底部菜单和一个BottomSheetBehavior关联起来
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomLayout);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Log.d("BottomSheet", "newState=" + i);
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                Log.d("BottomSheet", "onSlide=" + v);
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
