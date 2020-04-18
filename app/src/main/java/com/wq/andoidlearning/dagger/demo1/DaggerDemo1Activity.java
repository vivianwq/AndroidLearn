package com.wq.andoidlearning.dagger.demo1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class DaggerDemo1Activity extends AppCompatActivity {
    public static final String TAG = DaggerDemo1Activity.class.getSimpleName();
    private Button btnGetData;
    private Button btnGetNetData;
    private TextView tvContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_demo1);
        btnGetData = findViewById(R.id.btnGetData);
        btnGetNetData = findViewById(R.id.btnGetNetData);
        tvContent = findViewById(R.id.tvContent);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataRepository dataRepository = new DataRepository();
                String data = dataRepository.getData();
                tvContent.setText(data);
            }
        });

        btnGetNetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataRepository dataRepository = new DataRepository();
                String data = dataRepository.getNetData();
                tvContent.setText(data);
            }
        });

    }
}
