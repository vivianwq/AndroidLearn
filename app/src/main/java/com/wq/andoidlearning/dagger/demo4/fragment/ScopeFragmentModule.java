package com.wq.andoidlearning.dagger.demo4.fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ScopeFragmentModule {

    @Provides
    @PerScopeFragment
    ScopeFragmentData provideScopeFragmentData() {
        return new ScopeFragmentData();
    }
}
