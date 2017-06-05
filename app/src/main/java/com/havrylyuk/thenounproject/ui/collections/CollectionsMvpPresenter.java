package com.havrylyuk.thenounproject.ui.collections;

import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 27.05.2017.
 */

public interface CollectionsMvpPresenter<V extends CollectionsMvpView> extends Presenter<V> {

    void setActivityTitle(String title);
}
