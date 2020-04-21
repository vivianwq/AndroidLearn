package com.wq.andoidlearning.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wq.andoidlearning.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemo6Activity extends AppCompatActivity {

    private Button btnRetry;
    public static final String TAG = "RxJavaDemo6Activity";
    public static final String MSG_WAIT_SHORT = "wait_short";
    public static final String MSG_WAIT_LONG = "wait_long";
    public static final String[] MSG_ARRAY = new String[]{
            MSG_WAIT_SHORT,
            MSG_WAIT_SHORT,
            MSG_WAIT_LONG,
            MSG_WAIT_LONG
    };
    private CompositeDisposable compositeDisposable;
    private int msgIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo6);
        btnRetry = findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRetryRequest();
            }
        });
        compositeDisposable = new CompositeDisposable();
    }

    private void startRetryRequest() {
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                int msgLen = MSG_ARRAY.length;
                doWork();
                //模拟请求的结果,前四次都返回失败,并将失败信息递交给retryWhen
                if (msgIndex < msgLen) {
                    //模拟请求失败的情况
                    e.onError(new Throwable(MSG_ARRAY[msgIndex]));
                    msgIndex++;
                } else {
                    //模拟成功的情况
                    e.onNext("Work Success");
                    e.onComplete();
                }
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            private int retryCount;

            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        String errorMsg = throwable.getMessage();
                        long waitTime = 0;
                        switch (errorMsg) {
                            case MSG_WAIT_SHORT:
                                waitTime = 2000;
                                break;
                            case MSG_WAIT_LONG:
                                waitTime = 4000;
                                break;
                            default:
                                break;
                        }
                        Log.d(TAG, "发生错误,尝试等待时间=" + waitTime + ",当前重试次数=" + retryCount);
                        retryCount++;
                        return waitTime > 0 && retryCount <= 4 ? Observable.timer(waitTime, TimeUnit.MILLISECONDS) : Observable.error(throwable);
                    }
                });
            }
        });
        DisposableObserver<String> disposableObserver = new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {
                Log.d(TAG, "DisposableObserver onNext=" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "DisposableObserver onError=" + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "DisposableObserver onComplete");

            }
        };
        stringObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);
    }

    private void doWork() {
        long workTime = (long) (Math.random() * 500) + 500;
        try {
            Log.d(TAG, "doWork start,threadId= " + Thread.currentThread().getId());
            Thread.sleep(workTime);
            Log.d(TAG, "doWork finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
