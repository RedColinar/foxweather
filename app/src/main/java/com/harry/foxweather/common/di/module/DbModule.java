package com.harry.foxweather.common.di.module;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.harry.data.area.database.entity.Province;
import com.harry.data.area.database.entity.ProvinceStorIOSQLiteDeleteResolver;
import com.harry.data.area.database.entity.ProvinceStorIOSQLiteGetResolver;
import com.harry.data.area.database.entity.ProvinceStorIOSQLitePutResolver;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Harry on 2017/9/24.
 */

@Module
public class DbModule {
    @Provides
    @NonNull
    @Singleton
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite
                .builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .defaultScheduler(null)
                .addTypeMapping(Province.class,
                        SQLiteTypeMapping
                                .<Province>builder()
                                .putResolver(new ProvinceStorIOSQLitePutResolver())
                                .getResolver(new ProvinceStorIOSQLiteGetResolver())
                                .deleteResolver(new ProvinceStorIOSQLiteDeleteResolver())
                                .build())
                .build();
    }
}
