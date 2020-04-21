package com.wq.andoidlearning.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemo9Activity extends AppCompatActivity {
    public static final String TAG = RxJavaDemo9Activity.class.getSimpleName();

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo9);
        compositeDisposable = new CompositeDisposable();
        findViewById(R.id.btnDemo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeDemo1();
            }
        });
        findViewById(R.id.btnDemo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeDemo2();
            }
        });

        findViewById(R.id.btnDemo3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeDemo3();
            }
        });

        findViewById(R.id.btnDemo4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeDemo4();
            }
        });
        findViewById(R.id.btnDemo5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimeDemo5();
            }
        });
    }

    //延时1s后执行一个任务,然后结束
    private void startTimeDemo1() {
        Log.d(TAG, "startTimeDemo1");
        DisposableObserver<Long> disposableObserver = getTimeDemoObserver();
        Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    //每隔1s执行一次任务,每一次任务执行前有1s的间隔,执行无限次
    private void startTimeDemo2() {
        Log.d(TAG, "startTimeDemo2");
        DisposableObserver<Long> disposableObserver = getTimeDemoObserver();
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    //每隔1s执行一次任务,立即执行第一次任务,执行无限次
    private void startTimeDemo3() {
        Log.d(TAG, "startTimeDemo3");
        DisposableObserver<Long> disposableObserver = getTimeDemoObserver();
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    //每隔1s执行一次任务,立即执行第一次任务,只执行五次
    private void startTimeDemo4() {
        Log.d(TAG, "startTimeDemo4");
        DisposableObserver<Long> disposableObserver = getTimeDemoObserver();
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .take(5)
                .subscribeOn(Schedulers.io())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    //先执行一个任务,等待1s,再执行另一个任务,然后结束
    private void startTimeDemo5() {
        Log.d(TAG, "startTimeDemo5");
        DisposableObserver<Long> disposableObserver = getTimeDemoObserver();
        Observable.just(0L)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "执行第一个任务");
                    }
                })
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    private DisposableObserver<Long> getTimeDemoObserver() {
        return new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "DisposableObserver,onNext=" + aLong + ",threadId=" + Thread.currentThread().getId());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "DisposableObserver,onError=" + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "DisposableObserver,onComplete");
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
