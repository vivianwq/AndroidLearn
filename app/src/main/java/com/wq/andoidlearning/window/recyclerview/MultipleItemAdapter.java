package com.wq.andoidlearning.window.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wq.andoidlearning.R;

import java.util.ArrayList;

public class MultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private ArrayList<String> mTitles;

    public MultipleItemAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("MultipleItemAdapter","onCreateViewHolder");
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
        } else {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("MultipleItemAdapter","onBindViewHolder"+position);
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mTitles.get(position));
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mTextView.setText(mTitles.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
//        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
        return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public void addAll(ArrayList<String> list) {
        if (mTitles != null) {
//            mTitles.clear();
        } else {
            mTitles = new ArrayList<>();
        }
        mTitles.addAll(list);
    }



    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        TextViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.text_view);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        ImageViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.text_view);
            mImageView = view.findViewById(R.id.image_view);
        }
    }
}