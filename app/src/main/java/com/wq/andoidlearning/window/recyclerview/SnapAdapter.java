package com.wq.andoidlearning.window.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

import java.util.ArrayList;

public class SnapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private ArrayList<String> mTitles;

    public SnapAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text_large, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TextViewHolder) holder).mTextView.setText(mTitles.get(position));
    }


    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public void addAll(ArrayList<String> list) {
        if (mTitles != null) {
            mTitles.clear();
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


}