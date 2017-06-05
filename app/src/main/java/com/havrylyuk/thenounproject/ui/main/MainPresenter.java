package com.havrylyuk.thenounproject.ui.main;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 21.05.2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
    implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(CompositeDisposableHelper compositeDisposableHelper,
                         DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void setUserInfo() {
        getMvpView().setUserName(getDataManager().getUserName());
    }

    @Override
    public void setCurrentTitle(String title) {
        if (title != null) {
            getMvpView().setActivityTitle(title);
        }
    }

}
