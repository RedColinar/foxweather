package com.harry.foxweather.gson.weatherData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Harry on 2017/9/28.
 */

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public  Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecasts;
}
