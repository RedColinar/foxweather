package com.harry.foxweather.gson.weatherData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Harry on 2017/9/27.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
