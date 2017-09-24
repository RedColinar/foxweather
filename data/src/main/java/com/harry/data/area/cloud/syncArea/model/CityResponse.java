package com.harry.data.area.cloud.syncArea.model;

import com.harry.data.area.database.entity.City;

import java.util.List;

/**
 * Created by Harry on 2017/9/24.
 */

public class CityResponse {
    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
