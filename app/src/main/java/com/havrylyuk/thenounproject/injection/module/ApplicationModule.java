package com.havrylyuk.thenounproject.injection.module;

import android.app.Application;
import android.content.Context;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.AppDataManager;
import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.local.db.DbHelper;
import com.havrylyuk.thenounproject.data.local.preferences.PreferencesHelper;
import com.havrylyuk.thenounproject.data.remote.ApiHelper;
import com.havrylyuk.thenounproject.injection.qualifier.ApplicationContext;
import com.havrylyuk.thenounproject.injection.scope.TheNounProjectAppScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

@Module(includes = {
    ContextModule.class, PreferencesModule.class, NetworkModule.class, DatabaseModule.class
})
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application getApplication() {
        return application;
    }

    @Provides
    @TheNounProjectAppScope
    CalligraphyConfig getCalligraphyConfig() {
        return new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }


    @Provides
    @TheNounProjectAppScope
    AppDataManager getAppDataManager(
            @ApplicationContext Context context, PreferencesHelper preferencesHelper, DbHelper dbHelper,
            ApiHelper apiHelper) {
        return new AppDataManager(context, dbHelper, preferencesHelper, apiHelper);
    }

    @Provides
    @TheNounProjectAppScope
    DataManager getDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}


