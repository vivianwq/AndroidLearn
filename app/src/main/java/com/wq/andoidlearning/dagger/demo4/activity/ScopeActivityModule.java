package com.wq.andoidlearning.dagger.demo4.activity;

import dagger.Module;
import dagger.Provides;

@Module
public class ScopeActivityModule {

    @Provides
    @PerScopeActivity
    public ScopeActivitySharedData provideScopeActivityData() {
        return new ScopeActivitySharedData();
    }

    @Provides
    public ScopeActivityNormalData provideScopeActivityNormalData() {
        return new ScopeActivityNormalData();
    }
}
