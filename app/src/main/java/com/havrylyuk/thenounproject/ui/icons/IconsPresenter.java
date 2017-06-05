package com.havrylyuk.thenounproject.ui.icons;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public class IconsPresenter <V extends IconsMvpView> extends BasePresenter<V>
        implements IconsMvpPresenter<V> {

    @Inject
    public IconsPresenter(CompositeDisposableHelper compositeDisposableHelper, DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void setActivityTitle(String title) {
        if (title != null) {
            getMvpView().setActivityTitle(title);
        }
    }
}
