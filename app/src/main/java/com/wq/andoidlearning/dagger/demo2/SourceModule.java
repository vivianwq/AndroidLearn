package com.wq.andoidlearning.dagger.demo2;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class SourceModule {

    @Provides
    @Named("Local")
    public Source provideLocalSource() {
        return new LocalSource();
    }

    @Provides
    @Named("Remote")
    public Source provideRemoteSource() {
        return new RemoteSource();
    }
}
