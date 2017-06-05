package com.havrylyuk.thenounproject.injection.module;

import android.content.Context;

import com.havrylyuk.thenounproject.injection.qualifier.ApplicationContext;
import com.havrylyuk.thenounproject.injection.scope.TheNounProjectAppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @TheNounProjectAppScope
    @ApplicationContext
    public Context getApplicationContext() {
        return context;
    }
}
