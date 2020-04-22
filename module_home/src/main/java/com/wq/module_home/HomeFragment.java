package com.wq.module_home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wq.lib_base.ConstantMap;
import com.wq.lib_base.RouterMap;
import com.wq.lib_base.bean.SerialBean;
import com.wq.lib_base.service.StoreModuleRouterService;
import com.wq.lib_base.util.Utils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

@Route(path = RouterMap.HOME_FRAGMENT)
public class HomeFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(container.getContext())
                .inflate(R.layout.fragment_home, container, false);
        viewGroup.findViewById(R.id.btnNoResult).setOnClickListener(this);
        viewGroup.findViewById(R.id.btnResultClient).setOnClickListener(this);
        viewGroup.findViewById(R.id.btnEventBus).setOnClickListener(this);
        viewGroup.findViewById(R.id.btnInterButton).setOnClickListener(this);
        viewGroup.findViewById(R.id.btnInject).setOnClickListener(this);
        return viewGroup;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnNoResult) {
            ARouter
                    .getInstance()
                    .build(RouterMap.NO_RESULT_ACTIVITY)
                    .navigation();
        } else if (id == R.id.btnResultClient) {
            getActivity().startActivity(new Intent(getActivity(), ResultClientActivity.class));
        } else if (id == R.id.btnEventBus) {
            ARouter
                    .getInstance()
                    .build(RouterMap.EVENT_BUS_ACTIVITY)
                    .withInt(ConstantMap.EVENT_BUS_DATA, 1000)
                    .navigation();
        } else if (id == R.id.btnInterButton) {
            ARouter.getInstance()
                    .build(RouterMap.INTER_TARGET_ACTIVITY)
                    .withBoolean(ConstantMap.IS_LOGIN,
                            StoreModuleRouterService.isLogin())
                    .navigation();
        } else if (id == R.id.btnInject) {
            SerialBean bean = new SerialBean();
            bean.setName("SerialBean");
            bean.setAge(18);
            ARouter
                    .getInstance()
                    .build(RouterMap.INJECT_ACTIVITY)
                    .withInt(ConstantMap.INJECT_AGE, 18)
                    .withObject(ConstantMap.INJECT_OBJECT, bean)
                    .navigation();
        }

    }

    @Subscriber(tag = ConstantMap.EVENT_BUS_KEY)
    public void onEvent(String s) {
        Utils.toast(getContext(), s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
