package com.wq.andoidlearning.rxjava.demo10;

import io.reactivex.observables.ConnectableObservable;

public interface IHolder {
    void onWorkerPrepared(ConnectableObservable<String> workerFlow);
}
