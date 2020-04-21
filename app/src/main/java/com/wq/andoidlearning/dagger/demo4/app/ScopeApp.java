package com.wq.andoidlearning.dagger.demo4.app;

import android.app.Application;

public class ScopeApp extends Application {
    private ScopeAppComponent scopeAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        scopeAppComponent = DaggerScopeAppComponent.builder().scopeAppModule(new ScopeAppModule()).build();
    }

    public ScopeAppComponent getAppComponent() {
        return scopeAppComponent;
    }
}
