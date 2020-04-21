package com.wq.andoidlearning.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wq.andoidlearning.R;
import com.wq.andoidlearning.rxjava.demo4.NewsResultEntity;
import com.wq.andoidlearning.rxjava.demo4.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemo8Activity extends AppCompatActivity {
    public static final String TAG = "RxJavaDemo8Activity";
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<NewsResultEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo8);
        initView();
        findViewById(R.id.btnConcat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshArticleUseContact();
            }
        });
        findViewById(R.id.btnConcatEager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshArticleUseConcatEager();
            }
        });
        findViewById(R.id.btnMerge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshArticleUseMerge();
            }
        });
        findViewById(R.id.btnPublish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshArticleUsePublish();
            }
        });
    }

    private void initView() {

        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false));
        recyclerViewAdapter = new RecyclerViewAdapter(list);
        rv.setAdapter(recyclerViewAdapter);

    }

    private void refreshArticleUseContact() {
        Observable<List<NewsResultEntity>> concat =
                Observable.concat(getCacheArticle(2000)
                                .subscribeOn(Schedulers.io())
                        , getNetWorkArticle(500)
                                .subscribeOn(Schedulers.io())
                );
        DisposableObserver<List<NewsResultEntity>>
                disposableObserver = getArticleObserver();
        concat.observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    private void refreshArticleUseConcatEager() {
        List<Observable<List<NewsResultEntity>>> observables = new ArrayList<>();
        observables.add(getCacheArticle(2000)
                .subscribeOn(Schedulers.io()));
        observables.add(getNetWorkArticle(500)
                .subscribeOn(Schedulers.io()));

        Observable<List<NewsResultEntity>> listObservable =
                Observable.concatEager(observables);
        DisposableObserver<List<NewsResultEntity>>
                disposableObserver = getArticleObserver();
        listObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    private void refreshArticleUseMerge() {
        Observable<List<NewsResultEntity>> concat =
                Observable.merge(getCacheArticle(2500)
                                .subscribeOn(Schedulers.io())
                        , getNetWorkArticle(500)
                                .subscribeOn(Schedulers.io())
                );
        DisposableObserver<List<NewsResultEntity>>
                disposableObserver = getArticleObserver();
        concat.observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    private void refreshArticleUsePublish() {
        Observable<List<NewsResultEntity>> publishObservable
                = getNetWorkArticle(500)
                .subscribeOn(Schedulers.io())
                .publish(new Function<Observable<List<NewsResultEntity>>, ObservableSource<List<NewsResultEntity>>>() {
                    @Override
                    public ObservableSource<List<NewsResultEntity>> apply(Observable<List<NewsResultEntity>> listObservable) throws Exception {
                        return Observable.merge(listObservable
                                , getCacheArticle(2000)
                                        .subscribeOn(Schedulers.io()).takeUntil(listObservable))
                                ;
                    }
                });
        DisposableObserver<List<NewsResultEntity>>
                disposableObserver = getArticleObserver();
        publishObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    //模拟缓存数据源
    private Observable<List<NewsResultEntity>> getCacheArticle(final long simulateTime) {
        return Observable.create(new ObservableOnSubscribe<List<NewsResultEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsResultEntity>> observableEmitter) throws Exception {
                try {
                    Log.d(TAG, "开始加载缓存数据");
                    Thread.sleep(simulateTime);
                    List<NewsResultEntity> results = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        NewsResultEntity entity = new NewsResultEntity();
                        entity.setType("缓存");
                        entity.setDesc("序号=" + i);
                        results.add(entity);
                    }
                    observableEmitter.onNext(results);
                    observableEmitter.onComplete();
                    Log.d(TAG, "结束加载缓存数据");
                } catch (InterruptedException e) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(e);
                    }
                }
            }
        });
    }


    //模拟网络数据源
    private Observable<List<NewsResultEntity>> getNetWorkArticle(final long simulateTime) {
        return Observable.create(new ObservableOnSubscribe<List<NewsResultEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsResultEntity>> observableEmitter) throws Exception {
                try {
                    Log.d(TAG, "开始加载网络数据");
                    Thread.sleep(simulateTime);
                    List<NewsResultEntity> results = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        NewsResultEntity entity = new NewsResultEntity();
                        entity.setType("网络");
                        entity.setDesc("序号=" + i);
                        results.add(entity);
                    }
                    //a.正常情况
//                    observableEmitter.onNext(results);
//                    observableEmitter.onComplete();
                    //b.发生异常
                    observableEmitter.onError(new Throwable("newWork Error"));
                    Log.d(TAG, "结束加载网络数据");
                } catch (InterruptedException e) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(e);
                    }
                }
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<NewsResultEntity>>>() {
            @Override
            public ObservableSource<? extends List<NewsResultEntity>> apply(Throwable throwable) throws Exception {
                Log.d(TAG, "网络请求发生错误throwable=" + throwable);
                return Observable.never();
            }
        });
    }

    private DisposableObserver<List<NewsResultEntity>> getArticleObserver() {
        return new DisposableObserver<List<NewsResultEntity>>() {
            @Override
            public void onNext(List<NewsResultEntity> newsResultEntities) {
                list.clear();
                list.addAll(newsResultEntities);
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "加载错误,e=" + e);

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "加载完成");
            }
        };
    }
}
