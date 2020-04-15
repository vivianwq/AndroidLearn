package com.wq.andoidlearning.materialdesign.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<TextItemViewHolder> {
    String[] items;

    public RecyclerViewAdapter(String[] items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TextItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TextItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextItemViewHolder holder, int position) {
        holder.bind(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
