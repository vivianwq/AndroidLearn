package com.wq.andoidlearning.rxjava.demo4;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;

public class TextItemViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private TextView item_type;

    public TextItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.list_item);
        item_type=itemView.findViewById(R.id.item_type);
    }

    public void bind(NewsResultEntity text) {
        textView.setText(text.getDesc());
        item_type.setText(text.getType());
    }
}
