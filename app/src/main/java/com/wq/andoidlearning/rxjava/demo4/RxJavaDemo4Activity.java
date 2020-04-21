package com.wq.andoidlearning.rxjava.demo4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wq.andoidlearning.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJavaDemo4Activity extends AppCompatActivity {
    private int currentPage = 1;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<NewsResultEntity> list = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo4);
        initView();
    }

    private void initView() {
        Button btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshArticle(++currentPage);

            }
        });
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false));
        recyclerViewAdapter = new RecyclerViewAdapter(list);
        rv.setAdapter(recyclerViewAdapter);
        refreshArticle(++currentPage);

    }

    private void refreshArticle(final int page) {
        Observable<List<NewsResultEntity>> listObservable = Observable.just(page)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<List<NewsResultEntity>>>() {

                    @Override
                    public ObservableSource<List<NewsResultEntity>> apply(Integer integer) throws Exception {
                        Observable<NewsEntity> androidNews = getObservable("Android", page);
                        Observable<NewsEntity> iosNews = getObservable("iOS", page);
                        return Observable.zip(androidNews, iosNews, new BiFunction<NewsEntity, NewsEntity, List<NewsResultEntity>>() {
                            @Override
                            public List<NewsResultEntity> apply(NewsEntity newsEntity, NewsEntity newsEntity2) throws Exception {
                                List<NewsResultEntity> result = new ArrayList<>();
                                result.addAll(newsEntity.getResults());
                                result.addAll(newsEntity2.getResults());
                                return result;
                            }
                        });
                    }
                });
        DisposableObserver<List<NewsResultEntity>> disposableObserver = new DisposableObserver<List<NewsResultEntity>>() {

            @Override
            public void onNext(List<NewsResultEntity> newsResultEntities) {
                list.clear();
                list.addAll(newsResultEntities);
                recyclerViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        listObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        compositeDisposable.add(disposableObserver);

    }

    private Observable<NewsEntity> getObservable(String category, int page) {
        NewsApi newsApi = new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewsApi.class);
        return newsApi.getNews(category, 10, page);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
