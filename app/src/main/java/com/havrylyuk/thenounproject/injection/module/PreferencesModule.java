package com.havrylyuk.thenounproject.injection.module;

import android.content.Context;

import com.havrylyuk.thenounproject.data.local.preferences.AppPreferencesHelper;
import com.havrylyuk.thenounproject.data.local.preferences.CommonPreferencesHelper;
import com.havrylyuk.thenounproject.data.local.preferences.PreferencesHelper;
import com.havrylyuk.thenounproject.injection.qualifier.ApplicationContext;
import com.havrylyuk.thenounproject.injection.scope.TheNounProjectAppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */
@Module(includes = ContextModule.class)
public class PreferencesModule {

    @Provides
    @TheNounProjectAppScope
    CommonPreferencesHelper getPreferencesHelper(
            @ApplicationContext Context context) {
        return new CommonPreferencesHelper(context);
    }

    @Provides
    @TheNounProjectAppScope
    PreferencesHelper getApplicationPreferences(
            CommonPreferencesHelper commonPreferencesHelper) {
        return new AppPreferencesHelper(commonPreferencesHelper);
    }

}
