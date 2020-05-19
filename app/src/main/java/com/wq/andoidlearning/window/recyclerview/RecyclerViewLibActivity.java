package com.wq.andoidlearning.window.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.window.recyclerview.lib.collapsing.EightCollapsingActivity;
import com.wq.andoidlearning.window.recyclerview.lib.delete.DeleteAndTopActivity;
import com.wq.andoidlearning.window.recyclerview.lib.header.HeaderFooterActivity;
import com.wq.andoidlearning.window.recyclerview.lib.horizontal.FourHorizontalActivity;
import com.wq.andoidlearning.window.recyclerview.lib.insert.ThirdInsertActivity;
import com.wq.andoidlearning.window.recyclerview.lib.layout.LayoutActivity;
import com.wq.andoidlearning.window.recyclerview.lib.load.LoadMoreActivity;
import com.wq.andoidlearning.window.recyclerview.lib.load.LoadMoreActivity2;
import com.wq.andoidlearning.window.recyclerview.lib.multistyle.FiveMultiStyleActivity;
import com.wq.andoidlearning.window.recyclerview.lib.normal.NormalRecyclerViewActivity;
import com.wq.andoidlearning.window.recyclerview.lib.normal.SpanRecyclerViewActivity;
import com.wq.andoidlearning.window.recyclerview.lib.refresh.RefreshAndMoreActivity1;
import com.wq.andoidlearning.window.recyclerview.lib.refresh.RefreshAndMoreActivity2;
import com.wq.andoidlearning.window.recyclerview.lib.refresh.RefreshAndMoreActivity3;
import com.wq.andoidlearning.window.recyclerview.lib.scroll.ScrollActivity;
import com.wq.andoidlearning.window.recyclerview.lib.staggered.SevenStaggeredActivity;
import com.wq.andoidlearning.window.recyclerview.lib.staggered.SevenStaggeredGridActivity;
import com.wq.andoidlearning.window.recyclerview.lib.staggered.StageredLoadMoreActivity;
import com.wq.andoidlearning.window.recyclerview.lib.sticky.SixStickyHeaderActivity;
import com.wq.andoidlearning.window.recyclerview.lib.sticky.SixStickyNormalActivity;
import com.wq.andoidlearning.window.recyclerview.lib.sticky.SixStickyViewActivity;
import com.wq.andoidlearning.window.recyclerview.lib.tag.TagRecyclerViewActivity;
import com.wq.andoidlearning.window.recyclerview.lib.touchmove.NightTouchMoveActivity;
import com.wq.andoidlearning.window.recyclerview.lib.touchmove.NightTouchMoveActivity2;
import com.wq.andoidlearning.window.recyclerview.lib.type.HomePageActivity;
import com.wq.andoidlearning.window.recyclerview.lib.type.TypeActivity;

import org.yczbj.ycrefreshviewlib.utils.RefreshLogUtils;


public class RecyclerViewLibActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_lib);
        RefreshLogUtils.setLog(true);
        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void init() {
        findViewById(R.id.tv_1_1).setOnClickListener(this);
        findViewById(R.id.tv_1_2).setOnClickListener(this);
        findViewById(R.id.tv_1_3).setOnClickListener(this);
        findViewById(R.id.tv_2).setOnClickListener(this);
        findViewById(R.id.tv_3).setOnClickListener(this);
        findViewById(R.id.tv_4).setOnClickListener(this);
        findViewById(R.id.tv_5_1).setOnClickListener(this);
        findViewById(R.id.tv_6_1).setOnClickListener(this);
        findViewById(R.id.tv_6_2).setOnClickListener(this);
        findViewById(R.id.tv_6_3).setOnClickListener(this);
        findViewById(R.id.tv_7).setOnClickListener(this);
        findViewById(R.id.tv_7_2).setOnClickListener(this);
        findViewById(R.id.tv_7_3).setOnClickListener(this);
        findViewById(R.id.tv_8).setOnClickListener(this);
        findViewById(R.id.tv_9).setOnClickListener(this);
        findViewById(R.id.tv_9_2).setOnClickListener(this);
        findViewById(R.id.tv_10).setOnClickListener(this);
        findViewById(R.id.tv_11).setOnClickListener(this);
        findViewById(R.id.tv_11_2).setOnClickListener(this);
        findViewById(R.id.tv_12).setOnClickListener(this);
        findViewById(R.id.tv_13_1).setOnClickListener(this);
        findViewById(R.id.tv_13_2).setOnClickListener(this);
        findViewById(R.id.tv_14).setOnClickListener(this);
        findViewById(R.id.tv_15).setOnClickListener(this);
        findViewById(R.id.tv_16).setOnClickListener(this);
        findViewById(R.id.tv_17).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_1_1:
                startActivity(new Intent(this, RefreshAndMoreActivity1.class));
                break;
            case R.id.tv_1_2:
                startActivity(new Intent(this, RefreshAndMoreActivity2.class));
                break;
            case R.id.tv_1_3:
                startActivity(new Intent(this, RefreshAndMoreActivity3.class));
                break;
            case R.id.tv_2:
                startActivity(new Intent(this, HeaderFooterActivity.class));
                break;
            case R.id.tv_3:
                startActivity(new Intent(this, ThirdInsertActivity.class));
                break;
            case R.id.tv_4:
                startActivity(new Intent(this, FourHorizontalActivity.class));
                break;
            case R.id.tv_5_1:
                startActivity(new Intent(this, FiveMultiStyleActivity.class));
                break;
            case R.id.tv_6_1:
                startActivity(new Intent(this, SixStickyHeaderActivity.class));
                break;
            case R.id.tv_6_2:
                startActivity(new Intent(this, SixStickyViewActivity.class));
                break;
            case R.id.tv_6_3:
                startActivity(new Intent(this, SixStickyNormalActivity.class));
                break;
            case R.id.tv_7:
                startActivity(new Intent(this, SevenStaggeredGridActivity.class));
                break;
            case R.id.tv_7_2:
                startActivity(new Intent(this, SevenStaggeredActivity.class));
                break;
            case R.id.tv_7_3:
                startActivity(new Intent(this, StageredLoadMoreActivity.class));
                break;
            case R.id.tv_8:
                startActivity(new Intent(this, EightCollapsingActivity.class));
                break;
            case R.id.tv_9:
                startActivity(new Intent(this, NightTouchMoveActivity.class));
                break;
            case R.id.tv_9_2:
                startActivity(new Intent(this, NightTouchMoveActivity2.class));
                break;
            case R.id.tv_10:
                startActivity(new Intent(this, DeleteAndTopActivity.class));
                break;
            case R.id.tv_11:
                startActivity(new Intent(this, NormalRecyclerViewActivity.class));
                break;
            case R.id.tv_11_2:
                startActivity(new Intent(this, SpanRecyclerViewActivity.class));
                break;
            case R.id.tv_12:
                startActivity(new Intent(this, TypeActivity.class));
                break;
            case R.id.tv_13_1:
                startActivity(new Intent(this, LoadMoreActivity.class));
                break;
            case R.id.tv_13_2:
                startActivity(new Intent(this, LoadMoreActivity2.class));
                break;
            case R.id.tv_14:
                startActivity(new Intent(this, LayoutActivity.class));
                break;
            case R.id.tv_15:
                startActivity(new Intent(this, HomePageActivity.class));
                break;
            case R.id.tv_16:
                startActivity(new Intent(this, TagRecyclerViewActivity.class));
                break;
            case R.id.tv_17:
                startActivity(new Intent(this, ScrollActivity.class));
                break;
            default:
                break;
        }
    }


}
