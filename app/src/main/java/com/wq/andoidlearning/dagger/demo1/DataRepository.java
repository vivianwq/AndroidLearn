package com.wq.andoidlearning.dagger.demo1;

import javax.inject.Inject;

public class DataRepository {

    @Inject
    LocalSource localSource;

    @Inject
    RemoteSource remoteSource;

    public DataRepository() {
        DaggerSourceComponent.create().inject(this);
    }


    public String getData() {
        return localSource.getData();
    }

    public String getNetData() {
        return remoteSource.getData();
    }
}
