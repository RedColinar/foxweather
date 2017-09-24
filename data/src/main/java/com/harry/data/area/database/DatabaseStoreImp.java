package com.harry.data.area.database;

import com.harry.data.area.database.entity.Province;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Harry on 2017/9/24.
 */

public class DatabaseStoreImp implements DatabaseStore {

    @Inject
    StorIOSQLite storIOSQLite;

    @Override
    public Observable<PutResults> saveProvinces(List<Province> provinces) {
        if (provinces == null) {
            return Observable.just(null);
        }

        return storIOSQLite
                .put()
                .objects(provinces)
                .prepare()
                .asRxObservable()
                .map(provincesPutResults -> PutResults.newInstance(provincesPutResults.results()));
    }
}
