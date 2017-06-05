package com.havrylyuk.thenounproject.ui.base;

import android.support.annotation.NonNull;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 *
 * Created by Igor Havrylyuk on 20.05.2017.
 */
public class BasePresenter<V extends BaseMvpView> implements Presenter<V> {

    private CompositeDisposableHelper compositeDisposableHelper;
    private DataManager dataManager;

    @Inject
    public BasePresenter(CompositeDisposableHelper compositeDisposableHelper, DataManager dataManager) {
        this.compositeDisposableHelper = compositeDisposableHelper;
        this.dataManager = dataManager;
    }

    private V mvpView;

    @Override
    public void attachView(@NonNull V mvpView) {
        this.mvpView = mvpView;
        Timber.i("attachView()");
    }

    @Override
    public void detachView() {
        mvpView = null;
        Timber.i("detachView()");
        compositeDisposableHelper.dispose();
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public V getMvpView() {
        return mvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseMvpView) before"
                    + " requesting data to the Presenter");
        }
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposableHelper getCompositeDisposableHelper() {
        return compositeDisposableHelper;
    }

    public void setCompositeDisposableHelper(CompositeDisposableHelper compositeDisposableHelper) {
        this.compositeDisposableHelper = compositeDisposableHelper;
    }

}

