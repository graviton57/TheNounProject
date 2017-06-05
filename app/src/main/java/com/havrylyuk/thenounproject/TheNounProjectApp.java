package com.havrylyuk.thenounproject;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.havrylyuk.thenounproject.injection.component.ApplicationComponent;
import com.havrylyuk.thenounproject.injection.component.DaggerApplicationComponent;
import com.havrylyuk.thenounproject.injection.module.ApplicationModule;
import com.havrylyuk.thenounproject.injection.module.ContextModule;
import com.havrylyuk.thenounproject.injection.module.DatabaseModule;
import com.havrylyuk.thenounproject.injection.module.NetworkModule;

import javax.inject.Inject;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Igor Havrylyuk on 18.05.2017.
 */

public class TheNounProjectApp extends Application {

    private static ApplicationComponent applicationComponent;

    @Inject
    CalligraphyConfig calligraphyConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);//Init Fresco
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .contextModule(new ContextModule(this))
                .databaseModule(new DatabaseModule())
                .networkModule(new NetworkModule())
                .build();
        applicationComponent.inject(this);
        CalligraphyConfig.initDefault(calligraphyConfig);
        Timber.plant(new Timber.DebugTree());
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        TheNounProjectApp.applicationComponent = applicationComponent;
    }
}
