package com.wq.andoidlearning.bitmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.bitmap.demo1.BigImageViewActivity;
import com.wq.andoidlearning.bitmap.demo2.RecyclerViewBitmapActivity;
import com.wq.andoidlearning.bitmap.demo3.BigImageView2Activity;
import com.wq.andoidlearning.bitmap.demo4.ScaleTypeActivity;
import com.wq.andoidlearning.bitmap.demo5.Demo5Activity;
import com.wq.andoidlearning.doodle.DoodleActivity;

public class BitmapMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_main);
        logDensityInfo(this);
        logWrapperImageView();
        findViewById(R.id.btnLoadLarge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BitmapMainActivity.this, BigImageViewActivity.class));
            }
        });

        findViewById(R.id.btnDemo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BitmapMainActivity.this, BigImageView2Activity.class));
            }
        });
        findViewById(R.id.btnDemo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BitmapMainActivity.this, RecyclerViewBitmapActivity.class));
            }
        });

        findViewById(R.id.btnDemo3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BitmapMainActivity.this, ScaleTypeActivity.class));
            }
        });
        findViewById(R.id.btnDemo5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BitmapMainActivity.this, Demo5Activity.class));
            }
        });
        findViewById(R.id.btnDemo6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BitmapMainActivity.this, DoodleActivity.class));
            }
        });
    }

    private void logWrapperImageView() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a_small, options);
        Log.d("logWrapperImageView", "width=" + bitmap.getWidth() + ",height="
                + bitmap.getHeight() + ",size=" + bitmap.getByteCount());
    }

    public static void logDensityInfo(AppCompatActivity appCompatActivity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将信息保存到displayMetrics中
        appCompatActivity.getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        //1.x轴和y轴的dpi
        Log.d("logDensityInfo", "xdpi=" + displayMetrics.xdpi);
        Log.d("logDensityInfo", "ydpi=" + displayMetrics.ydpi);
        //2.x轴和y轴的像素个数
        Log.d("logDensityInfo", "widthPixels=" + displayMetrics.widthPixels);
        Log.d("logDensityInfo", "heightPixels=" + displayMetrics.heightPixels);
        //3.dpi
        Log.d("logDensityInfo", "densityDpi=" + displayMetrics.densityDpi);
        //4.dpi/160
        Log.d("logDensityInfo", "density=" + displayMetrics.density);
        //5.通常情况下和density相同
        Log.d("logDensityInfo", "scaledDensity=" + displayMetrics.scaledDensity);
    }
}
