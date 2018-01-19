package com.harry.foxweather.http.api;

import com.harry.foxweather.db.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by harry on 9/30/17.
 */

public interface CountryHttp {
    @GET("{provindeCode}/{cityCode}/")
    Call<List<Country>> get(int provinceCode,int cityCode);
}
