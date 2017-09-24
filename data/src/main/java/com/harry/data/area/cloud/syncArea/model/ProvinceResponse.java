package com.harry.data.area.cloud.syncArea.model;

import com.harry.data.area.database.entity.Province;

import java.util.List;


/**
 * Created by Harry on 2017/9/24.
 */

public class ProvinceResponse {
    private List<Province> provinces;

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}
