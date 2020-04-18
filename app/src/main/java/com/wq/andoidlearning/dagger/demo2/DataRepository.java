package com.wq.andoidlearning.dagger.demo2;


import javax.inject.Inject;
import javax.inject.Named;

public class DataRepository {

    @Inject
    @Named("Local")
    Source localSource;

    @Inject
    @Named("Remote")
    Source remoteSource;

    public DataRepository() {
        DaggerSourceComponent.create().inject(this);
    }

    public String getLocalData() {
        return localSource.getData();
    }

    public String getRemoteData() {
        return remoteSource.getData();
    }
}
