package com.wq.andoidlearning.rxjava;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RxJavaDemo3Activity extends AppCompatActivity {

    private EditText etSearch;
    private TextView tvContent;
    private PublishSubject<String> publishSubject;
    private DisposableObserver<String> disposableObserver;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo3);
        etSearch = findViewById(R.id.etSearch);
        tvContent = findViewById(R.id.tvContent);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                startSearch(s.toString());

            }
        });
        publishSubject = PublishSubject.create();
        disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                tvContent.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        publishSubject
                .debounce(200, TimeUnit.MICROSECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() > 0;
                    }
                })
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return getSearchObservable(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposableObserver);
    }

    private void startSearch(String query) {
        publishSubject.onNext(query);
    }

    private Observable<String> getSearchObservable(final String query) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                Log.d("RxJavaDemo3Activity", "开始请求,关键词为:" + query);

                try {
                    Thread.sleep(2000 + (long) Math.random() * 500);
                } catch (InterruptedException e) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(e);
                    }
                }
                Log.d("RxJavaDemo3Activity", "结束请求,关键词为:" + query);
                observableEmitter.onNext("完成搜索,关键词为:" + query);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
