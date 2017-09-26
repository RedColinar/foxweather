package com.harry.domain.area;

import android.content.Context;

import com.harry.data.area.cloud.syncArea.model.ProvinceResponse;
import com.harry.data.area.database.entity.Province;
import com.harry.data.common.reactive.ErrorResponse;
import com.harry.data.common.reactive.HttpSubscriber;
import com.harry.domain.area.usecase.SyncProvince;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Harry on 2017/9/24.
 */

public class SyncDataManager {

    @Inject
    SyncProvince syncProvince;

    @Inject
    Context context;

    private List<Province> provinces;

    public List<Province> syncProvince() {
        provinces = new ArrayList<>();
        syncProvince
                .setSwitchThreads(false)
                .execute()
                .subscribe(new HttpSubscriber<ProvinceResponse>(context) {
                    @Override
                    public void onError(ErrorResponse errorResponse) {}
                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(ProvinceResponse provinceResponse) {
                        List<Province> provinceLists = provinceResponse.getProvinces();
                        provinces.addAll(provinceLists);
                    }
                })
                .unsubscribe();
        return provinces;
    }
}
