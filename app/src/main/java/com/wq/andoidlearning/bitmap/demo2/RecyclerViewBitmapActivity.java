package com.wq.andoidlearning.bitmap.demo2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

import java.io.IOException;
import java.io.InputStream;

public class RecyclerViewBitmapActivity extends AppCompatActivity {

    public static final String TAG = "RecyclerViewBitmapActivity";
    private RecyclerView rv;
    private ImageAdapter imageAdapter;
    private SparseArray<Bitmap> bitmapSparseArray = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_bitmap);


        rv = findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        loadPic();

    }

    //加载图片
    private void loadPic() {
        InputStream is = null;
        String picName = "b.jpg";
        BitmapRegionDecoder regionDecoder;
        try {
            is = getAssets().open(picName);
            regionDecoder = BitmapRegionDecoder.newInstance(is, false);
            //获取图片的真实宽高
            final int width = regionDecoder.getWidth();
            final int height = regionDecoder.getHeight();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = calculateInSampleSize(width);

            //期望item的高度值
            //只考虑长图,高度比宽度大很多
            final int desiredItemHeight = width;

            //余数
            final int remainder = height % desiredItemHeight;

            //切割后图片数量,也就是item的数量
            final int bmpCount = height / desiredItemHeight;
            for (int i = 0; i < bmpCount; i++) {
                Rect rect;
                //如果总长度能够整除期望item高度,那么每块bitmap的高度相同=desiredItemHeight
                //如果两者不能整除,那么最后一块bitmap的高度=desiredItemHeight+remainder(余数)
                if (i == bmpCount - 1) {
                    rect = new Rect(0, desiredItemHeight * i, width, desiredItemHeight * (i + 1) + remainder);
                } else {
                    rect = new Rect(0, desiredItemHeight * i, width, desiredItemHeight * (i + 1));
                }
                Bitmap bitmap = regionDecoder.decodeRegion(rect, options);
                bitmapSparseArray.append(i, bitmap);
            }

            imageAdapter = new ImageAdapter(bitmapSparseArray);
            //图片高度是固定的,通过此属性来提高性能
            rv.setAdapter(imageAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 计算采样率
     *
     * @param actualWidth
     * @return
     */
    private int calculateInSampleSize(int actualWidth) {
        //获取手机屏幕的分辨率
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int screenWidth = displayMetrics.widthPixels;
        //图片在手机上是全屏显示的,因此宽度是固定的,根据屏幕宽度跟图片实际宽度
        //来计算采样率(inSampleSize)
        final double ratio = actualWidth / screenWidth;
        return (int) (ratio + 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int size = 0;
        if (bitmapSparseArray != null
                && (size = bitmapSparseArray.size()) > 0) {
            for (int i = 0; i < size; i++) {
                Bitmap bitmap = bitmapSparseArray.get(i);
                if (bitmap != null) {
                    //recycle之后,虚拟机gc的时候就会回收这部分内容
                    bitmap.recycle();
                }
            }
        }
    }
}
