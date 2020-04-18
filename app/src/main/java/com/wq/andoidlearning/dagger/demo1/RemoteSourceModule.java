package com.wq.andoidlearning.dagger.demo1;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteSourceModule {

    @Provides
    public RemoteSource provideRemoteSource(){
        return new RemoteSource();
    }
}
