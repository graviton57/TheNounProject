package com.havrylyuk.thenounproject.data.remote.helper;

import com.havrylyuk.thenounproject.utils.reactive.BaseSchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */

public class CompositeDisposableHelper {

    private final CompositeDisposable disposables;
    private final BaseSchedulerProvider schedulerProvider;

    @Inject
    public CompositeDisposableHelper(CompositeDisposable disposables,
                                     BaseSchedulerProvider schedulerProvider) {
      this.disposables = disposables;
      this.schedulerProvider = schedulerProvider;
    }

    private final ObservableTransformer schedulersTransformer = new ObservableTransformer() {
      @Override
      public ObservableSource apply(Observable upstream) {
        return upstream.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui());
      }
    };

    @SuppressWarnings("unchecked")
    public <T> ObservableTransformer<T, T> applySchedulers() {
      return (ObservableTransformer<T, T>) schedulersTransformer;
    }

    public void addDisposable(Disposable disposable) {
      disposables.add(disposable);
    }

    public void dispose() {
      if (!disposables.isDisposed()) {
        disposables.dispose();
      }
    }

    public BaseSchedulerProvider getSchedulerProvider() {
      return schedulerProvider;
    }

}
