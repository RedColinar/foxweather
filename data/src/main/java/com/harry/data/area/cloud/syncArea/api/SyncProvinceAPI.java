package com.harry.data.area.cloud.syncArea.api;

import com.harry.data.area.cloud.syncArea.model.ProvinceResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Harry on 2017/9/24.
 */

public interface SyncProvinceAPI {
    @GET("http://guolin.tech/api/china")
    Observable<ProvinceResponse> sync();
}
