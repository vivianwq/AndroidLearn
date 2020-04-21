package com.wq.andoidlearning.dagger.demo4.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ScopeAppModule {

    @Provides
    @Singleton
    public ScopeAppData provideScopeAppData() {
        return new ScopeAppData();
    }
}
