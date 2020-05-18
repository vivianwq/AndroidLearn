package com.wq.andoidlearning.component.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

//Fragment的基类
public abstract class BaseFragment extends Fragment {

    private View contentView;

    //是否启用懒加载
    private boolean isLazyLoad;


    //设置加载的布局id
    protected abstract int setLayoutId();

    //初始化操作
    protected abstract void init();

    //findViewById
    protected View findViewById(int id) {
        return contentView.findViewById(id);
    }

    //懒加载数据
    protected abstract void loadData();


    //是否启动懒加载
    public void setLazyLoad(boolean lazyLoad) {
        isLazyLoad = lazyLoad;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().post(new ServiceBean("onAttach--->" + this.hashCode() + "\n"));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new ServiceBean("onStart--->" + this.hashCode() + "\n"));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().post(new ServiceBean("onCreate--->" + this.hashCode() + "\n"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().post(new ServiceBean("onCreateView--->" + this.hashCode() + "\n"));
        contentView = inflater.inflate(setLayoutId(), container, false);
        return contentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().post(new ServiceBean("onViewCreated--->" + this.hashCode() + "\n"));
        //初始化
        init();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().post(new ServiceBean("onPause--->" + this.hashCode() + "\n"));
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().post(new ServiceBean("onStop--->" + this.hashCode() + "\n"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().post(new ServiceBean("onDestroyView--->" + this.hashCode() + "\n"));
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new ServiceBean("onResume--->" + this.hashCode() + "\n"));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().post(new ServiceBean("onActivityCreated--->" + this.hashCode() + "\n"));
        //如果不是懒加载模式,创建就加载数据
        if (!isLazyLoad) {
            loadData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        EventBus.getDefault().post(new ServiceBean("setUserVisibleHint--->" + this.hashCode() + ":" + isVisibleToUser + "\n"));
    }


}
