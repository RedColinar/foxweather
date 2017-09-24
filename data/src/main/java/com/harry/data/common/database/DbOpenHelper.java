package com.harry.data.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.harry.data.area.database.table.CityTable;
import com.harry.data.area.database.table.CountryTable;
import com.harry.data.area.database.table.ProvinceTable;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String NAME = "fox_weather_db";
    private static final Integer VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProvinceTable.creatTable());
        db.execSQL(CityTable.creatTable());
        db.execSQL(CountryTable.creatTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
