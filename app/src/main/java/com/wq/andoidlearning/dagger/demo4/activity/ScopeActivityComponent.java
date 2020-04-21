package com.wq.andoidlearning.dagger.demo4.activity;

import com.wq.andoidlearning.dagger.demo4.app.ScopeAppComponent;
import com.wq.andoidlearning.dagger.demo4.fragment.ScopeFragmentComponent;

import dagger.Component;

@Component(dependencies = {ScopeAppComponent.class}, modules = {ScopeActivityModule.class})
@PerScopeActivity
public interface ScopeActivityComponent {
    void inject(ScopeActivity scopeActivity);

    ScopeFragmentComponent scopeFragmentComponent();
}
