package com.wq.andoidlearning.dagger.demo4.app;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ScopeAppModule.class})
public interface ScopeAppComponent {

    //如果它被其他的Component依赖,那么需要声明getXXX方法
    ScopeAppData getScopeAppData();
}
