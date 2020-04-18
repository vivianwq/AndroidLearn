package com.wq.andoidlearning.materialdesign.floating;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class FABMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabmain);

        findViewById(R.id.btnDemo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FABMainActivity.this,
                        FloatingButtonActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnDemo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FABMainActivity.this,
                        FloatingButton2Activity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnDemo3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FABMainActivity.this,
                        FloatingButton3Activity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnDemo4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FABMainActivity.this,
                        FloatingButton4Activity.class);
                startActivity(intent);

            }
        });
    }
}
