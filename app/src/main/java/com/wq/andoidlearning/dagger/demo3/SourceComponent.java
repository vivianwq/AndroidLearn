package com.wq.andoidlearning.dagger.demo3;

import dagger.Component;

@Component(dependencies = DependencyComponent.class
        , modules = SourceModule.class)
public interface SourceComponent {
    void inject(DataRepository dataRepository);

    //SubSourceComponent为子Component
    SubSourceComponent getSubSourceComponent();
}
