package com.wq.andoidlearning.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemo1Activity extends AppCompatActivity {

    private Button btnDownLoad;
    private TextView tvDownLoadResult;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo1);
        btnDownLoad=findViewById(R.id.btnDownLoad);
        tvDownLoadResult=findViewById(R.id.tvDownLoadResult);
        btnDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownLoad();
            }
        });
    }

    private void startDownLoad() {
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 100; i++) {
                    if (i % 20 == 0) {
                        try {
                            //模拟下载的操作
                            Thread.sleep(500);
                        } catch (InterruptedException exception) {
                            if (!e.isDisposed()) {
                                e.onError(exception);
                            }
                        }
                        e.onNext(i);
                    }
                }
                e.onComplete();
            }
        });

        DisposableObserver<Integer> disposableObserver = new DisposableObserver<Integer>() {

            @Override
            public void onNext(Integer integer) {
                Log.d("RxJavaDemo1Activity", "onNext=" + integer);
                tvDownLoadResult.setText("Current Progress=" + integer);

            }

            @Override
            public void onError(Throwable e) {
                Log.d("RxJavaDemo1Activity", "onError=" + e);
                tvDownLoadResult.setText("DownLoad Error");
            }

            @Override
            public void onComplete() {
                Log.d("RxJavaDemo1Activity", "onComplete");
                tvDownLoadResult.setText("DownLoad onComplete");
            }
        };
        integerObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
