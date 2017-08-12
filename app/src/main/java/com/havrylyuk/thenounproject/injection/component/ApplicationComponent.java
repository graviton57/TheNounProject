package com.havrylyuk.thenounproject.injection.component;

import android.content.Context;

import com.havrylyuk.thenounproject.TheNounProjectApp;
import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.injection.module.ApplicationModule;
import com.havrylyuk.thenounproject.injection.qualifier.ApplicationContext;
import com.havrylyuk.thenounproject.injection.scope.TheNounProjectAppScope;


import dagger.Component;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

@TheNounProjectAppScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent  {

    void inject(TheNounProjectApp application);

    @ApplicationContext
    Context context();

    DataManager getDataManager();

}
