package com.harry.data.area.cloud;

import com.harry.data.area.cloud.syncArea.api.SyncProvinceAPI;
import com.harry.data.area.cloud.syncArea.model.ProvinceResponse;
import com.harry.data.common.network.RestfulServiceBase;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Harry on 2017/9/24.
 */

public class CloudStoreImp extends RestfulServiceBase implements CloudStore {
    private SyncProvinceAPI syncProvinceAPI;

    @Inject
    Retrofit retrofit;

    @Override
    public void initializeRetrofitAPIs() {
        syncProvinceAPI = retrofit.create(SyncProvinceAPI.class);
    }

    @Override
    public Observable<ProvinceResponse> syncProvince() {
        return syncProvinceAPI.sync()
                .doOnNext(new Action1<ProvinceResponse>() {
                    @Override
                    public void call(ProvinceResponse provinceResponse) {
                        // here do nothing
                    }
                });
    }
}
