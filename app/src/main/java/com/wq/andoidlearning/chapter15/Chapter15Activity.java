package com.wq.andoidlearning.chapter15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.chapter15.download.single.SingleDownloadActivity;

public class Chapter15Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter15);
        findViewById(R.id.btnSp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chapter15Activity.this,
                        SpMainActivity.class));

            }
        });
        findViewById(R.id.btnDownLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chapter15Activity.this,
                        SingleDownloadActivity.class));

            }
        });
    }
}
