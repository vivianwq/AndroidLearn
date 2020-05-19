package com.wq.andoidlearning.window.recyclerview.lib.staggered;

import android.content.Context;
import android.view.ViewGroup;

import com.wq.andoidlearning.window.recyclerview.lib.data.PictureData;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.holder.BaseViewHolder;


public class ImageAdapter extends RecyclerArrayAdapter<PictureData> {
    public ImageAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(parent);
    }
}
