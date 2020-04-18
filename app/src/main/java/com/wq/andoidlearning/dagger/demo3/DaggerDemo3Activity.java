package com.wq.andoidlearning.dagger.demo3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class DaggerDemo3Activity extends AppCompatActivity {

    private Button btnGetData;
    private Button btnGetNetData;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_demo3);

        btnGetData = findViewById(R.id.btnGetData);
        btnGetNetData = findViewById(R.id.btnGetNetData);
        tvContent = findViewById(R.id.tvContent);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataRepository dataRepository = new DataRepository();
                String data = dataRepository.getDependencyData();
                tvContent.setText(data);
            }
        });

        btnGetNetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubRepository dataRepository = new SubRepository();
                String data = dataRepository.getLocalData();
                tvContent.setText(data);
            }
        });
    }
}
