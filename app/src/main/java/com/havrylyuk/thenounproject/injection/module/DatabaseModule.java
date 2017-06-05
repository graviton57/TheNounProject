package com.havrylyuk.thenounproject.injection.module;

import android.content.Context;

import com.havrylyuk.thenounproject.data.local.db.AppDbHelper;
import com.havrylyuk.thenounproject.data.local.db.DbHelper;
import com.havrylyuk.thenounproject.data.local.db.model.DaoMaster;
import com.havrylyuk.thenounproject.data.local.db.model.DaoSession;
import com.havrylyuk.thenounproject.injection.qualifier.ApplicationContext;
import com.havrylyuk.thenounproject.injection.scope.TheNounProjectAppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */
@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Provides
    @TheNounProjectAppScope
    public DaoSession daoSession(@ApplicationContext Context context) {
        return new DaoMaster(new DaoMaster.DevOpenHelper(context, "test").getWritableDb()).newSession();
    }

    @Provides
    @TheNounProjectAppScope
    public DbHelper dbHelper(DaoSession daoSession) {
        return new AppDbHelper(daoSession);
    }

}
