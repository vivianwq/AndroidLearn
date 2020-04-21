package com.wq.andoidlearning.rxjava.demo11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wq.andoidlearning.R;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJavaDemo11Activity extends AppCompatActivity {

    public static final String TAG = RxJavaDemo11Activity.class.getName();
    private static final long[] CITY_ARRAY = new long[]{
            101010100L,
            101010100L,
            101010100L,
            101030100L

    };
    private CompositeDisposable compositeDisposable;
    private TextView tvResult;
    private PublishSubject<Boolean> netStatusPublish;
    private PublishSubject<Long> cityPublish;
    private BroadcastReceiver receiver;
    private long cacheCity = -1;
    private Thread locationThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo11);
        tvResult = findViewById(R.id.tvResult);
        netStatusPublish = PublishSubject.create();
        cityPublish = PublishSubject.create();
        compositeDisposable = new CompositeDisposable();
        registerBroadCast();
        startUpdateWeather();
        startUpdateLocation();

    }

    private void startUpdateWeather() {
        Observable.merge(getCityPublish(),
                getNetStatusPublish())
                .flatMap(new Function<Long, ObservableSource<WeatherEntity>>() {
                    @Override
                    public ObservableSource<WeatherEntity> apply(Long aLong) throws Exception {

                        Log.d(TAG, "尝试请求天气信息=" + aLong);
                        return getWeather(aLong).subscribeOn(Schedulers.io());
                    }
                }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {

                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        Log.d(TAG, "请求天气信息过程中发生错误,进行重订阅");
                        return Observable.just(0);
                    }
                });
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WeatherEntity weatherEntity) {

                        WeatherEntity.WeatherInfo info = weatherEntity.getWeatherinfo();
                        if (info != null) {
                            Log.d(TAG, "尝试请求天气信息成功");
                            StringBuilder builder = new StringBuilder();
                            builder.append("城市名:")
                                    .append(info.getCity())
                                    .append("\n")
                                    .append("温度：")
                                    .append(info.getTemp())
                                    .append("\n")
                                    .append("风向：")
                                    .append(info.getWD())
                                    .append("\n")
                                    .append("风速：")
                                    .append(info.getWS())
                                    .append("\n");
                            tvResult.setText(builder.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "尝试请求天气信息失败");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "尝试请求天气信息结束");

                    }
                });
    }

    private Observable<Long> getCityPublish() {
        return cityPublish
                .distinctUntilChanged()
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        setCacheCity(aLong);
                    }
                });
    }

    private Observable<Long> getNetStatusPublish() {
        return netStatusPublish.filter(new Predicate<Boolean>() {
            @Override
            public boolean test(Boolean aBoolean) throws Exception {
                return aBoolean && getCacheCity() > 0;
            }
        }).map(new Function<Boolean, Long>() {
            @Override
            public Long apply(Boolean aBoolean) throws Exception {
                return getCacheCity();
            }
        }).subscribeOn(Schedulers.io());
    }


    private Observable<WeatherEntity> getWeather(long cityId) {
        WeatherApi weatherApi = new Retrofit.Builder()
                .baseUrl("http://www.weather.com.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(WeatherApi.class);
        return weatherApi.getWeather(cityId);
    }

    //模拟定位模块的回调
    private void startUpdateLocation() {
        locationThread = new Thread() {
            @Override
            public void run() {
                //实例一:
                while (true) {
                    try {
                        for (long cityId : CITY_ARRAY) {
                            if (isInterrupted()) {
                                break;
                            }
                            Log.d(TAG, "重新定位");
                            Thread.sleep(1000);
                            Log.d(TAG, "定位到城市信息=" + cityId);
                            cityPublish.onNext(cityId);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //实例二
//                cityPublish.onNext(CITY_ARRAY[0]);

            }
        };
        locationThread.start();

    }

    private void registerBroadCast() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (netStatusPublish != null) {
                    netStatusPublish.onNext(isNetWorkConnected());
                }
            }
        };
        IntentFilter filter = new IntentFilter(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
        registerReceiver(receiver, filter);
    }

    public void unRegisterBroadCast() {
        unregisterReceiver(receiver);
    }

    public long getCacheCity() {
        return cacheCity;
    }

    public void setCacheCity(long cacheCity) {
        this.cacheCity = cacheCity;
    }

    private boolean isNetWorkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationThread.interrupt();
        unRegisterBroadCast();
        compositeDisposable.clear();
    }
}
