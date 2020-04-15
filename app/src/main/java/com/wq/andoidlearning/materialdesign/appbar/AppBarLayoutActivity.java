package com.wq.andoidlearning.materialdesign.appbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;
import com.wq.andoidlearning.R;

public class AppBarLayoutActivity extends AppCompatActivity {


    private AppBarLayout appbarLayout;
    private TextView tvMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        setAppBar();
    }

    private void setAppBar() {
        appbarLayout = findViewById(R.id.appbarLayout);
        tvMove = findViewById(R.id.tvMove);
        appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                Log.d("AppBarLayoutActivity", "offset=" + i);
                int height = tvMove.getHeight();
                int minHeight = tvMove.getMinHeight();
                float fraction = i / (float) (minHeight - height);
                tvMove.setAlpha(1 - fraction);
            }
        });
    }
}
