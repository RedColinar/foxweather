package com.harry.foxweather.http;

import com.harry.foxweather.http.api.CityHttp;
import com.harry.foxweather.http.api.CountryHttp;
import com.harry.foxweather.http.api.ProvincesHttp;
import com.harry.foxweather.http.api.WeatherHttp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by harry on 9/30/17.
 */

public class HttpManager {
    private static HttpManager httpManager;

    private HttpManager() {}

    public static HttpManager getInstantce() {
        if (httpManager == null) {
            return new HttpManager();
        } else {
            return httpManager;
        }
    }

    Retrofit areaRetrofit = new Retrofit.Builder()
            .baseUrl("http://guolin.tech/api/china/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Retrofit weatherRetrofit = new Retrofit.Builder()
            .baseUrl("http://guolin.tech/api/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ProvincesHttp provincesHttp = areaRetrofit.create(ProvincesHttp.class);
    public CityHttp cityHttp = areaRetrofit.create(CityHttp.class);
    public CountryHttp countryHttp = areaRetrofit.create(CountryHttp.class);
    public WeatherHttp weatherHttp = weatherRetrofit.create(WeatherHttp.class);
}
