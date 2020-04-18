package com.wq.andoidlearning.dagger.demo3;

import dagger.Module;
import dagger.Provides;

@Module
public class SourceModule {

    @Provides
    LocalSource provideLocalSource() {
        return new LocalSource();
    }

}
