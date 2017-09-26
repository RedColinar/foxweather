package com.harry.domain.area.usecase;

import com.harry.data.area.cloud.CloudStore;
import com.harry.data.area.cloud.syncArea.model.ProvinceResponse;
import com.harry.data.area.database.DatabaseStore;
import com.harry.domain.common.executor.PostExecutionThread;
import com.harry.domain.common.executor.ThreadExecutor;
import com.harry.domain.common.usecase.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Harry on 2017/9/24.
 */

public class SyncProvince extends UseCase<ProvinceResponse> {
    @Inject
    CloudStore cloudStore;

    public SyncProvince(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<ProvinceResponse> buildObservableUseCase() {
        return cloudStore.syncProvince();
    }
}
