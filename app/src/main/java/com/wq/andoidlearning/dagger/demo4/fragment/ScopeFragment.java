package com.wq.andoidlearning.dagger.demo4.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.dagger.demo4.activity.ScopeActivity;
import com.wq.andoidlearning.dagger.demo4.activity.ScopeActivityNormalData;
import com.wq.andoidlearning.dagger.demo4.activity.ScopeActivitySharedData;
import com.wq.andoidlearning.dagger.demo4.app.ScopeAppData;

import javax.inject.Inject;

public class ScopeFragment extends Fragment {
    private ScopeActivity scopeActivity;

    @Inject
    ScopeAppData scopeAppData;

    @Inject
    ScopeActivitySharedData scopeActivitySharedData;

    @Inject
    ScopeActivityNormalData scopeActivityNormalData;

    @Inject
    ScopeFragmentData scopeFragmentData;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        scopeActivity = (ScopeActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scope, container, false);
        scopeActivity.getScopeActivityComponent().scopeFragmentComponent().inject(this);
        TextView tv = rootView.findViewById(R.id.tv_scope_fragment);
        String result = "[ScopeFragment Space] \n scopeAppData=" + scopeAppData
                + "\n\n" + "scopeActivitySharedData=" + scopeActivitySharedData
                + "\n\n" + "scopeActivityNormalData=" + scopeActivityNormalData
                + "\n\n" + "scopeFragmentData=" + scopeFragmentData;
        tv.setText(result);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
