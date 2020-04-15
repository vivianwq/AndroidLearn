package com.wq.andoidlearning.materialdesign.behavior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.materialdesign.nested.NestedScrollActivity;
import com.wq.andoidlearning.materialdesign.nestedscrollview.NestedScrollViewActivity;
import com.wq.andoidlearning.materialdesign.recycler.RecyclerViewActivity;

public class BehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);

        findViewById(R.id.btnScroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BehaviorActivity.this,
                        NestedScrollActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnRecycler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BehaviorActivity.this,
                        RecyclerViewActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnNested).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BehaviorActivity.this,
                        NestedScrollViewActivity.class);
                startActivity(intent);

            }
        });
    }
}
