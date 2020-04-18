package com.wq.andoidlearning.materialdesign.bottomsheet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class BottomSheetMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_main);

        findViewById(R.id.btnDemo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BottomSheetMainActivity.this,
                        BottomSheetDialog1Activity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnDemo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BottomSheetMainActivity.this,
                        BottomSheetDialog2Activity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnDemo3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BottomSheetMainActivity.this,
                        BottomSheetDialog3Activity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.btnDemo4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BottomSheetMainActivity.this,
                        BottomSheetDialog4Activity.class);
                startActivity(intent);

            }
        });
    }
}
