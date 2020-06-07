package com.wq.andoidlearning;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NewMainActivity extends AppCompatActivity {
    private Button mBtnLogin = (Button) findViewById(R.id.btn3);
    int sum(int a, int b) {
        return a + b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        System.out.print(sum(1,2));
    }
}
