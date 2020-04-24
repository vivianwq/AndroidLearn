package com.wq.andoidlearning.bitmap.demo1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.io.IOException;

public class BigImageViewActivity extends AppCompatActivity {

    private LongImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image_view);
        iv=findViewById(R.id.iv);
        try {
            iv.setImage(getAssets().open("b.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
