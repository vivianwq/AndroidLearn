package com.wq.andoidlearning.component.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseLazyLoadFragment extends BaseFragment {
    private boolean isPrepare;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLazyLoad(true);
        isPrepare = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建时要判断是否已经显示给用户,加载数据
        onVisibleToUser();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //显示状态发生变化
        onVisibleToUser();
    }

    private void onVisibleToUser() {
        //如果已经初始化完成,并且显示给用户
        if (isPrepare && getUserVisibleHint()) {
            loadData();
        }
    }
}
