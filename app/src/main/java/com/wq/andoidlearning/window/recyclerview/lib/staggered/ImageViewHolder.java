package com.wq.andoidlearning.window.recyclerview.lib.staggered;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wq.andoidlearning.window.recyclerview.lib.data.PictureData;

import org.yczbj.ycrefreshviewlib.holder.BaseViewHolder;


public class ImageViewHolder extends BaseViewHolder<PictureData> {
    ImageView imgPicture;

    public ImageViewHolder(ViewGroup parent) {
        super(new ImageView(parent.getContext()));
        imgPicture = (ImageView) itemView;
        imgPicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(PictureData data) {
        ViewGroup.LayoutParams params = imgPicture.getLayoutParams();
        //计算View的高度
        int height = 300;
        params.height = height;
        imgPicture.setLayoutParams(params);
        Glide.with(getContext())
                .load(data.getImage())
//                .placeholder(R.drawable.bg_small_tree_min)
                .into(imgPicture);
    }
}
