package com.harry.data.area.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.harry.data.area.database.table.CountryTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

/**
 * Created by Harry on 2017/9/24.
 */
@StorIOSQLiteType(table = CountryTable.TABLE_NAME)
public class Country {
    @Expose
    @SerializedName("id")
    @StorIOSQLiteColumn(name = CountryTable.ID, key = true)
    protected Integer id;

    @Expose
    @SerializedName("countryName")
    @StorIOSQLiteColumn(name = CountryTable.COUNTRY_NAME)
    protected String countryName;

    @Expose
    @SerializedName("cityId")
    @StorIOSQLiteColumn(name = CountryTable.CITY_ID)
    protected Integer cityId;


    @Expose
    @SerializedName("weatherId")
    @StorIOSQLiteColumn(name = CountryTable.WEATHER_ID)
    protected Integer weatherId;

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }
}
