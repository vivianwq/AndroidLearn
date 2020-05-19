package com.wq.andoidlearning.window.recyclerview.lib.multistyle;

import android.content.Context;
import android.view.ViewGroup;

import com.wq.andoidlearning.window.recyclerview.lib.data.AdData;
import com.wq.andoidlearning.window.recyclerview.lib.data.PersonData;
import com.wq.andoidlearning.window.recyclerview.lib.refresh.PersonViewHolder;
import com.wq.andoidlearning.window.recyclerview.lib.staggered.AdViewHolder;
import com.wq.andoidlearning.window.recyclerview.lib.staggered.ImageViewHolder;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.holder.BaseViewHolder;

import java.security.InvalidParameterException;


public class PersonWithAdAdapter extends RecyclerArrayAdapter<Object> {

    public static final int TYPE_INVALID = 0;
    public static final int TYPE_AD = 1;
    public static final int TYPE_PERSON = 2;
    public PersonWithAdAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if(getItem(position) instanceof AdData){
            return TYPE_AD;
        }else if (getItem(position) instanceof PersonData){
            return TYPE_PERSON;
        }
        return TYPE_INVALID;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_PERSON:
                return new PersonViewHolder(parent);
            case TYPE_AD:
                return new AdViewHolder(parent);
            case TYPE_INVALID:
                return new ImageViewHolder(parent);
            default:
                throw new InvalidParameterException();
        }
    }
}
