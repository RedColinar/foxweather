package com.harry.foxweather.common.di.module;

import com.harry.data.area.cloud.CloudStore;
import com.harry.data.area.cloud.CloudStoreImp;
import com.harry.data.area.database.DatabaseStore;
import com.harry.data.area.database.DatabaseStoreImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Harry on 2017/9/24.
 */

@Module
public class ApplicationModule {
    @Provides
    CloudStore provideGroupCloudDataStore(CloudStoreImp cloudStoreImp) {
        cloudStoreImp.initializeRetrofitAPIs();
        return cloudStoreImp;
    }
    @Provides
    DatabaseStore provideGroupCloudDataStore(DatabaseStoreImp databaseStoreImp) {
        return databaseStoreImp;
    }
}
