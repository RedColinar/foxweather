package com.harry.data.area.database.table;

/**
 * Created by Harry on 2017/9/24.
 */

public class CityTable {
    public static final String TABLE_NAME = "city";
    public static final String ID = "id";
    public static final String CITY_NAME = "city_name";
    public static final String CITY_CODE = "city_code";
    public static final String PROVINCE_ID = "province_id";

    public static String creatTable() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY," +
                CITY_NAME + " TEXT," +
                CITY_CODE + " INTEGER," +
                PROVINCE_ID + " INTEGER" +
                " )";
    }
}
