package com.harry.data.area.database;

import com.harry.data.area.database.entity.Province;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;

import java.util.List;

import rx.Observable;

/**
 * Created by Harry on 2017/9/24.
 */

public interface DatabaseStore {
    Observable<PutResults> saveProvinces(List<Province> lists);
}
