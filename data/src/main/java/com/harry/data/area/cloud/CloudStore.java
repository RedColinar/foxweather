package com.harry.data.area.cloud;

import com.harry.data.area.cloud.syncArea.model.ProvinceResponse;

import rx.Observable;

/**
 * Created by Harry on 2017/9/24.
 */

public interface CloudStore {
    Observable<ProvinceResponse> syncProvince();
}
