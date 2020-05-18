package com.wq.andoidlearning.component.fragment.androidx;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class NewLazyFragment extends Fragment {
    private Context context;
    //是否是第一次架子啊
    private boolean isFirstLoad = true;
    private View contentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = LayoutInflater.from(context).inflate(setLayoutId(), null);
        return contentView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    //findViewById
    protected View findViewById(int id) {
        return contentView.findViewById(id);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            //将数据加载逻辑放到onResume()方法中
            loadData();
            isFirstLoad = false;
        }
    }

    protected abstract int setLayoutId();

    //初始化视图
    protected abstract void init();


    //初始化数据
    protected void loadData() {

    }


}
