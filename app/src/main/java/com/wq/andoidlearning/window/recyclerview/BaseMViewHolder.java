package com.wq.andoidlearning.window.recyclerview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;

public abstract class BaseMViewHolder<M> extends RecyclerView.ViewHolder {

    //SparseArray比HashMap更节省内存,在某些条件下性能更好,
    //只能存储key为int类型的数据
    //用来存放View以减少findViewById的次数
    private SparseArray<View> viewSparseArray;

    public BaseMViewHolder(@NonNull View itemView) {
        super(itemView);
        if (viewSparseArray == null) {
            viewSparseArray = new SparseArray<>();
        }
    }

    public BaseMViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
        if (viewSparseArray == null) {
            viewSparseArray = new SparseArray<>();
        }
    }

    //子类设置数据方法
    public void setDate(M data) {
    }

    //根据ID来获取View
    protected <T extends View> T getView(int viewId) {
        //先从缓存中找,找到的话则直接返回
        //如果找不到则findViewById,再把结果存入缓存中
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    protected Context getContext() {
        return itemView.getContext();
    }

    protected int getDataPosition(){
        RecyclerView.Adapter adapter = getOwnerAdapter();
//        if (adapter!=null && adapter instanceof RecyclerArrayAdapter){
//            return getAdapterPosition() - ((RecyclerArrayAdapter) adapter).getHeaderCount();
//        }
        return getAdapterPosition();
    }

    /**
     * 获取adapter对象
     * @param <T>
     * @return                  adapter
     */
    @Nullable
    private  <T extends RecyclerView.Adapter> T getOwnerAdapter(){
        RecyclerView recyclerView = getOwnerRecyclerView();
        //noinspection unchecked
        return recyclerView != null ? (T) recyclerView.getAdapter() : null;
    }

    @Nullable
    private RecyclerView getOwnerRecyclerView(){
        try {
            Field field = RecyclerView.ViewHolder.class.getDeclaredField("mOwnerRecyclerView");
            field.setAccessible(true);
            return (RecyclerView) field.get(this);
        } catch (NoSuchFieldException ignored) {
            ignored.printStackTrace();
        } catch (IllegalAccessException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    /**
     * 添加子控件的点击事件
     * @param viewId                        控件id
     */
    protected void addOnClickListener(@IdRes final int viewId) {
        final View view = getView(viewId);
        if (view != null) {
            if (!view.isClickable()) {
                view.setClickable(true);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getOwnerAdapter()!=null){
//                        if (((RecyclerArrayAdapter)getOwnerAdapter()).getOnItemChildClickListener() != null) {
//                            ((RecyclerArrayAdapter)getOwnerAdapter()).getOnItemChildClickListener()
//                                    .onItemChildClick(v, getDataPosition());
//                        }
                    }
                }
            });
        }
    }
}
