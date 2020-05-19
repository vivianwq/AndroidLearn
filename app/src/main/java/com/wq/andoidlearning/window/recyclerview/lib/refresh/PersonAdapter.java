package com.wq.andoidlearning.window.recyclerview.lib.refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wq.andoidlearning.R;
import com.wq.andoidlearning.window.recyclerview.lib.data.PersonData;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.holder.BaseViewHolder;


public class PersonAdapter extends RecyclerArrayAdapter<PersonData> {

    private PersonViewHolder viewHolder;

    public PersonAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new PersonViewHolder(parent);
        return viewHolder;
    }

    public PersonViewHolder getViewHolder() {
        return viewHolder;
    }

    public class PersonViewHolder extends BaseViewHolder<PersonData> {

        private TextView tv_title;
        private ImageView iv_news_image;
        private TextView tv_content;


        PersonViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_news);
            iv_news_image = getView(R.id.iv_news_image);
            tv_title = getView(R.id.tv_title);
            tv_content = getView(R.id.tv_content);

            addOnClickListener(R.id.iv_news_image);
            addOnClickListener(R.id.tv_title);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void setData(final PersonData person){
            Log.i("ViewHolder","position"+getDataPosition());
            if (person.getName()==null || person.getName().length()==0){
                tv_content.setText("小杨逗比"+getDataPosition());
            }else {
                tv_title.setText(person.getName());
            }
            if (person.getSign()==null || person.getSign().length()==0){
                tv_content.setText("这个是内容"+getDataPosition());
            }else {
                tv_content.setText(person.getSign());
            }


            Glide.with(getContext())
                    .load(person.getImage())
//                    .error(R.drawable.bg_small_tree_min)
//                    .placeholder(R.drawable.bg_small_tree_min)
                    .into(iv_news_image);

        }
    }


}
