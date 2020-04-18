package com.wq.andoidlearning.materialdesign.tablayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class TabLayoutMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_main);

        findViewById(R.id.btnDemo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabLayoutMainActivity.this,
                        TabLayoutActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnDemo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabLayoutMainActivity.this,
                        TabLayout2Activity.class);
                startActivity(intent);

            }
        });
    }
}
