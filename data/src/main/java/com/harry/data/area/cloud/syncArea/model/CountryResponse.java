package com.harry.data.area.cloud.syncArea.model;

import com.harry.data.area.database.entity.Country;

import java.util.List;

/**
 * Created by Harry on 2017/9/24.
 */

public class CountryResponse {
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
