package com.harry.foxweather.http.api;

import com.harry.foxweather.db.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by harry on 9/30/17.
 */

public interface CityHttp {
    @GET("{provinceCode}/")
    Call<List<City>> get(int provinceCode);
}
