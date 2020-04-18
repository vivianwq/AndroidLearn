package com.wq.andoidlearning.dagger.demo3;

import dagger.Module;
import dagger.Provides;

@Module
public class DependencyModule {

    @Provides
    DependencySource provideDependencySource() {
        return new DependencySource();
    }
}
