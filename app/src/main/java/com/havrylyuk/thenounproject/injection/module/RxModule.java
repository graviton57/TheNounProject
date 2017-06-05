package com.havrylyuk.thenounproject.injection.module;

import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.utils.reactive.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

@Module
public class RxModule {

    @Provides
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider getSchedulerProvider() {
        return new SchedulerProvider();
    }

    @Provides
    CompositeDisposableHelper getCompositeDisposableHelper(
            CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
        return new CompositeDisposableHelper(compositeDisposable, schedulerProvider);
    }

}
