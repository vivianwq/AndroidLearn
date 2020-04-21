package com.wq.andoidlearning.rxjava.demo11;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {
    @GET("adat/sk/{cityId}.html")
    Observable<WeatherEntity> getWeather(@Path("cityId") long cityId);
}
