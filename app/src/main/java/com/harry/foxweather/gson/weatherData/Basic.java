package com.harry.foxweather.gson.weatherData;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Harry on 2017/9/27.
 */

public class Basic {
    /*
      "basic": {
        "city": "青岛",
        "cnty": "中国",
        "id": "CN101120201",
        "lat": "36.088000",
        "lon": "120.343000",
        "prov": "山东",  //城市所属省份（仅限国内城市）
        "update": {
          "loc": "2016-08-30 11:52",
          "utc": "2016-08-30 03:52"
        }
      },
    */
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }
}
