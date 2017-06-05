package com.havrylyuk.thenounproject.utils.reactive;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 18.05.2017.
 */
public class SchedulerProvider implements BaseSchedulerProvider {

    @Inject
    public SchedulerProvider() {
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

}
