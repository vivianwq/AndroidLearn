package com.wq.andoidlearning.dagger.demo2;

import dagger.Component;

@Component(modules = SourceModule.class)
public interface SourceComponent {

    void inject(DataRepository dataRepository);
}
