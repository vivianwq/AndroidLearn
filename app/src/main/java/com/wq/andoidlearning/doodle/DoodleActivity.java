package com.wq.andoidlearning.doodle;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

public class DoodleActivity extends AppCompatActivity {

    private ImageView ivContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doodle);
        ivContent = findViewById(R.id.ivContent);
        ImageLoader.build(getApplicationContext())
                .bindBitmap("https://oimagec6.ydstatic.com/image?id=-7352554208008629997&product=adpublish&w=520&h=347", ivContent);
    }
}
