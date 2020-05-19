package com.wq.andoidlearning.window.recyclerview.lib.header;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wq.andoidlearning.window.recyclerview.lib.data.AdData;
import com.wq.andoidlearning.window.recyclerview.lib.data.DataProvider;
import com.yc.cn.ycbannerlib.banner.adapter.AbsStaticPagerAdapter;

import java.util.List;

public class BannerAdapter extends AbsStaticPagerAdapter {

    private Context ctx;
    private List<AdData> list;
       public BannerAdapter(Context ctx){
           this.ctx = ctx;
            list = DataProvider.getAdList();
        }

        @Override
        public View getView(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(ctx);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //加载图片
            Glide.with(ctx)
                    .load(list.get(position).getImage())
//                    .placeholder(R.drawable.default_image)
                    .into(imageView);
            //点击事件
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            return imageView;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }