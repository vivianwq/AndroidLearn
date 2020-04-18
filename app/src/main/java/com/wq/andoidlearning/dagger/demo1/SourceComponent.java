package com.wq.andoidlearning.dagger.demo1;

import dagger.Component;

@Component(modules = {RemoteSourceModule.class})
public interface SourceComponent {

    void inject(DataRepository dataRepository);
}
