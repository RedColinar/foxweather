package com.harry.data.area.database.table;

public class ProvinceTable {
    public static final String TABLE_NAME = "province";
    public static final String ID = "id";
    public static final String PROVINCE_NAME = "province_name";
    public static final String PROVINCE_CODE = "province_code";

    public static String creatTable() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY," +
                PROVINCE_NAME + " TEXT," +
                PROVINCE_CODE + " INTEGER" +
                " )";
    }
}
