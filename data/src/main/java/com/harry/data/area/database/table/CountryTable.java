package com.harry.data.area.database.table;

/**
 * Created by Harry on 2017/9/24.
 */

public class CountryTable {
    public static final String TABLE_NAME = "country";
    public static final String ID = "id";
    public static final String COUNTRY_NAME = "country_name";
    public static final String WEATHER_ID = "weather_id";
    public static final String CITY_ID = "province_id";

    public static String creatTable() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY," +
                COUNTRY_NAME + " TEXT," +
                WEATHER_ID + " INTEGER," +
                CITY_ID + " INTEGER" +
                " )";
    }
}
