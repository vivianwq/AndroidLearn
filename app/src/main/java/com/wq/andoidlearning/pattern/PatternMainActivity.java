package com.wq.andoidlearning.pattern;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.pattern.adapter.PatternDemo1Activity;

public class PatternMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_main);
        findViewById(R.id.btnAdapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatternMainActivity.this, PatternDemo1Activity.class);
                startActivity(intent);
            }
        });
    }
}
