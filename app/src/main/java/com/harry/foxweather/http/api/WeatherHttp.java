package com.harry.foxweather.http.api;

import com.harry.foxweather.gson.weatherData.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by harry on 9/30/17.
 */

public interface WeatherHttp {
    @GET
    Call<Weather> get(@Query("cityid") String weatherId, @Query("key") String key);
}
