package com.wq.andoidlearning.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.MainActivity;
import com.wq.andoidlearning.R;

public class Demo1Activity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RelativeLayout layoutTop;

    private Button btn1;
    private Button btn2;
    private Button btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);

        layoutTop = findViewById(R.id.layout_top);

        btn1 = findViewById(R.id.btn1);

        btn2 = findViewById(R.id.btn2);

        btn4 = findViewById(R.id.btn4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutTop.scrollTo(50, 50);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //scrollBy
                layoutTop.scrollBy(50, 50);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Demo1Activity.this, btn4.getText().toString() + "is clicked", Toast.LENGTH_LONG).show();
            }
        });


    }
}