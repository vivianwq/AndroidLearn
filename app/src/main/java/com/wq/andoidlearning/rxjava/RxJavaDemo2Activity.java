package com.wq.andoidlearning.rxjava;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

public class RxJavaDemo2Activity extends AppCompatActivity {

    private PublishSubject<Double> publishSubject;
    private CompositeDisposable compositeDisposable;
    private TextView tvContent;
    private SourceHandler sourceHandler;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo2);
        tvContent = findViewById(R.id.tvContent);
        publishSubject = PublishSubject.create();
        DisposableObserver<List<Double>> disposableObserver = new DisposableObserver<List<Double>>() {
            @Override
            public void onNext(List<Double> doubles) {

                double result = 0;
                if (doubles.size() > 0) {
                    for (Double d : doubles) {
                        result += d;
                    }
                    result = result / doubles.size();
                }
                Log.d("RxJavaDemo2Activity", "更新平均温度:" + result);
                tvContent.setText("过去3秒收到了" + doubles.size() + "个数据,平均温度为:" + result);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        publishSubject
                .buffer(3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposableObserver);
        //开始测量温度
        sourceHandler = new SourceHandler();
        sourceHandler.sendEmptyMessage(0);
    }

    public void updateTemperature(double temperature) {
        Log.d("RxJavaDemo2Activity", "温度测量结果:" + temperature);
        publishSubject.onNext(temperature);
    }

    private class SourceHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            double temperature = Math.random() * 25 + 5;
            updateTemperature(temperature);
            //循环地发送
            sendEmptyMessageDelayed(0, 250 + (long) (250 * Math.random()));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sourceHandler.removeCallbacksAndMessages(null);
        compositeDisposable.clear();
    }
}
