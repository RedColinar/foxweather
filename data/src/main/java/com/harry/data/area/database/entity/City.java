package com.harry.data.area.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.harry.data.area.database.table.CityTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by Harry on 2017/9/24.
 */

@StorIOSQLiteType(table = CityTable.TABLE_NAME)
public class City {

    @Expose
    @SerializedName("id")
    @StorIOSQLiteColumn(name = CityTable.ID, key = true)
    protected Integer id;

    @Expose
    @SerializedName("cityName")
    @StorIOSQLiteColumn(name = CityTable.CITY_NAME)
    protected String cityName;

    @Expose
    @SerializedName("cityCode")
    @StorIOSQLiteColumn(name = CityTable.CITY_CODE)
    protected Integer cityCode;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }
}
