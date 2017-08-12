package com.havrylyuk.thenounproject.ui.collections;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 27.05.2017.
 */

public class CollectionsPresenter<V extends CollectionsMvpView> extends BasePresenter<V>
        implements CollectionsMvpPresenter<V> {

    @Inject
    public CollectionsPresenter(CompositeDisposableHelper compositeDisposableHelper,
                                DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void setActivityTitle(String title) {
        if (title != null) {
            getMvpView().setActivityTitle(title);
        }
    }
}
