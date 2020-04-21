package com.wq.andoidlearning.rxjava.demo4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<TextItemViewHolder> {
    List<NewsResultEntity> items;

    public RecyclerViewAdapter(List<NewsResultEntity> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TextItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rx, parent, false);
        return new TextItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextItemViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
