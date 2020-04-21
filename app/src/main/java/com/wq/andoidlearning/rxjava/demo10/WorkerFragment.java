package com.wq.andoidlearning.rxjava.demo10;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

public class WorkerFragment extends Fragment {
    public static final String TAG = WorkerFragment.class.getName();

    private ConnectableObservable<String> worker;
    private Disposable disposable;
    private IHolder iHolder;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IHolder) {
            iHolder = (IHolder) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (worker != null) {
            return;
        }
        Bundle bundle = getArguments();
        final String taskName = (bundle != null ? bundle.getString("task_name") : null);
        worker = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int i = 0; i < 10; i++) {
                    String message = "任务名称=" + taskName + ",任务进度=" + i * 10 + "%";
                    try {
                        Log.d(TAG, message);
                        Thread.sleep(1000);
                        //如果已经抛弃,那么不再继续任务
                        if (e.isDisposed()) {
                            break;
                        }
                    } catch (InterruptedException error) {
                        if (!e.isDisposed()) {
                            e.onError(error);
                        }
                    }
                    e.onNext(message);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).publish();
        disposable = worker.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (iHolder != null) {
            iHolder.onWorkerPrepared(worker);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iHolder = null;
    }
}

