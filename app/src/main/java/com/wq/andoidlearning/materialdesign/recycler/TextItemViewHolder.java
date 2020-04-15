package com.wq.andoidlearning.materialdesign.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

public class TextItemViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public TextItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.list_item);
    }

    public void bind(String text) {
        textView.setText(text);
    }
}
