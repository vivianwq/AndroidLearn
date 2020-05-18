package com.wq.andoidlearning.window.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

import java.util.ArrayList;

public class GridItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private ArrayList<SpanModel> mTitles;

    public GridItemAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Text1ViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((Text1ViewHolder) holder).mTextView.setText(mTitles.get(position).getData());

    }


    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public void addAll(ArrayList<SpanModel> list) {
        if (mTitles != null) {
            mTitles.clear();
        } else {
            mTitles = new ArrayList<>();
        }
        mTitles.addAll(list);
    }

    public static class Text1ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        Text1ViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.text_view);
        }
    }


}