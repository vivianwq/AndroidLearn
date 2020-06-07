package com.wq.andoidlearning.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wq.andoidlearning.R;

import java.util.ArrayList;
import java.util.List;

public class ListMyAdapter extends BaseAdapter {
    private List<String> dataList = new ArrayList<>();
    private Context context;

    public ListMyAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 50; i++) {
            dataList.add("第" + i + "条数据");
        }
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
            convertView.setTag(viewHolder);
            viewHolder.text_view = convertView.findViewById(R.id.text_view);
            Log.i("ListMyAdapter", "未缓存" + position);
        } else {
            Log.i("ListMyAdapter", "已缓存" + position);
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text_view.setText("我是第" + position + "条数据");

        return convertView;
    }

    class ViewHolder {

        TextView text_view;


    }

}
