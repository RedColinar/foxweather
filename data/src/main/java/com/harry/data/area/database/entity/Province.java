package com.harry.data.area.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.harry.data.area.database.table.ProvinceTable;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

@StorIOSQLiteType(table = ProvinceTable.TABLE_NAME)
public class Province {
    @Expose
    @SerializedName("id")
    @StorIOSQLiteColumn(name = ProvinceTable.ID, key = true)
    protected Integer id;

    @Expose
    @SerializedName("provinceName")
    @StorIOSQLiteColumn(name = ProvinceTable.PROVINCE_NAME)
    protected String provinceName;

    @Expose
    @SerializedName("provinceCode")
    @StorIOSQLiteColumn(name = ProvinceTable.PROVINCE_CODE)
    protected String provinceCode;

    public Province() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
