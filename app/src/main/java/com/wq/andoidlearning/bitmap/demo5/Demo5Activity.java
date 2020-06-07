package com.wq.andoidlearning.bitmap.demo5;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.bitmap.BitmapCompressTools;

public class Demo5Activity extends AppCompatActivity {
    private ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5);
        ivImg = findViewById(R.id.ivImg);
        Bitmap bitmap = BitmapCompressTools.decodeSampledBitmapFromResource(
                getResources(), R.mipmap.load_info, 1080, 200);
        ivImg.setImageBitmap(bitmap);
    }
}
