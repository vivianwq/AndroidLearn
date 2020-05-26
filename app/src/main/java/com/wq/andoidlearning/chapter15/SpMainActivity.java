package com.wq.andoidlearning.chapter15;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class SpMainActivity extends AppCompatActivity {

    private TextView tvContent;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_main);
        tvContent = findViewById(R.id.tvContent);
        stringBuilder = new StringBuilder();

        findViewById(R.id.btn1000).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long startA = System.currentTimeMillis();
                for (int i = 0; i < 200; i++) {
                    SharedPreferences preferences = SpMainActivity.this.getSharedPreferences("testA", 0);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString("yc" + i, "yangchong" + i);
                    edit.commit();
                }
                long endA = System.currentTimeMillis();
                long a = endA - startA;
                Log.i("测试A", "----" + a);
                stringBuilder.append("测试A----" + a + "\n");


                long startB = System.currentTimeMillis();
                SharedPreferences preferencesB = SpMainActivity.this.getSharedPreferences("testB", 0);
                SharedPreferences.Editor editB = preferencesB.edit();
                for (int i = 0; i < 200; i++) {
                    editB.putString("yc" + i, "yangchong" + i);
                }
                editB.commit();
                long endB = System.currentTimeMillis();
                long b = endB - startB;
                Log.i("测试B", "----" + b);
                stringBuilder.append("测试B----" + b + "\n");

                long startC = System.currentTimeMillis();
                SharedPreferences.Editor editC = null;
                for (int i = 0; i < 200; i++) {
                    SharedPreferences preferencesC = SpMainActivity.this.getSharedPreferences("testC", 0);
                    if (editC == null) {
                        editC = preferencesC.edit();
                    }
                    editC.putString("yc" + i, "yangchong" + i);
                }
                editC.commit();
                long endC = System.currentTimeMillis();
                long c = endC - startC;
                Log.i("测试C", "----" + c);
                stringBuilder.append("测试C----" + c + "\n");
                tvContent.setText(stringBuilder.toString());

            }
        });
        findViewById(R.id.btnApply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long startA = System.currentTimeMillis();
                for (int i=0 ; i<200 ; i++){
                    SharedPreferences preferences = SpMainActivity.this.getSharedPreferences("testA", 0);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString("yc"+i,"yangchong"+i);
                    edit.apply();
                }
                long endA = System.currentTimeMillis();
                long a = endA - startA;
                Log.i("测试A","----"+a);
                stringBuilder.append("测试A----" + a + "\n");


                long startB = System.currentTimeMillis();
                SharedPreferences preferencesB = SpMainActivity.this.getSharedPreferences("testB", 0);
                SharedPreferences.Editor editB = preferencesB.edit();
                for (int i=0 ; i<200 ; i++){
                    editB.putString("yc"+i,"yangchong"+i);
                }
                editB.apply();
                long endB = System.currentTimeMillis();
                long b = endB - startB;
                Log.i("测试B","----"+b);
                stringBuilder.append("测试B----" + b + "\n");


                long startC = System.currentTimeMillis();
                SharedPreferences.Editor editC = null;
                for (int i=0 ; i<200 ; i++){
                    SharedPreferences preferencesC = SpMainActivity.this.getSharedPreferences("testC", 0);
                    if (editC==null){
                        editC = preferencesC.edit();
                    }
                    editC.putString("yc"+i,"yangchong"+i);
                }
                editC.apply();
                long endC = System.currentTimeMillis();
                long c = endC - startC;
                Log.i("测试C","----"+c);
                stringBuilder.append("测试C----" + c + "\n");
                tvContent.setText(stringBuilder);

            }
        });


    }


}
