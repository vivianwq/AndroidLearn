package com.wq.andoidlearning.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemo5Activity extends AppCompatActivity {

    private Button btnSimple, btnAdvance;
    private CompositeDisposable compositeDisposable;
    public static final String TAG = "RxJavaDemo5Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo5);
        btnSimple = findViewById(R.id.btnSimple);
        btnAdvance = findViewById(R.id.btnAdvance);
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSimplePolling();
            }
        });
        btnAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdvancePolling();
            }
        });
        compositeDisposable = new CompositeDisposable();
    }

    private void startAdvancePolling() {
        Log.d(TAG, "startAdvancePolling click");
        Observable<Long> longObservable = Observable.just(0L).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                doWork();
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<Long>>() {
            private long repeatCount;

            @Override
            public ObservableSource<Long> apply(Observable<Object> objectObservable) throws Exception {
                return objectObservable.flatMap(new Function<Object, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(Object o) throws Exception {
                        if (++repeatCount > 4) {
                            return Observable.error(new Throwable("Polling work finished"));
                        }
                        Log.d(TAG, "startAdvancePolling apply");
                        return Observable.timer(3000 + repeatCount * 1000, TimeUnit.MILLISECONDS);
                    }
                });
            }
        });
        DisposableObserver<Long> disposableObserver = getDisposableObserver();
        longObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    private void startSimplePolling() {
        Log.d(TAG, "startSimplePolling");
        Observable<Long> longObservable = Observable
                .intervalRange(0, 5, 0, 3000,
                        TimeUnit.MILLISECONDS)
                .take(5)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        doWork();
                    }
                });
        DisposableObserver<Long> disposableObserver = getDisposableObserver();
        longObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);

    }

    private DisposableObserver<Long> getDisposableObserver() {
        return new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "DisposableObserver onError,threadId=" + Thread.currentThread().getId());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "DisposableObserver onComplete,threadId=" + Thread.currentThread().getId());
            }
        };
    }

    private void doWork() {
        long workTime = (long) (Math.random() * 500) + 500;
        try {
            Log.d(TAG, "doWork start, threadId=" + Thread.currentThread().getId());
            Thread.sleep(workTime);
            Log.d(TAG, "doWork finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
