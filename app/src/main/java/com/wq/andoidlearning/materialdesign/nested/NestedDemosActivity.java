package com.wq.andoidlearning.materialdesign.nested;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.materialdesign.nested.demo1.Demo1Activity;
import com.wq.andoidlearning.materialdesign.nested.demo2.Demo2Activity;

public class NestedDemosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_demos);

        findViewById(R.id.btnDemo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestedDemosActivity.this,
                        Demo1Activity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnDemo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestedDemosActivity.this,
                        Demo2Activity.class);
                startActivity(intent);

            }
        });
    }
}
