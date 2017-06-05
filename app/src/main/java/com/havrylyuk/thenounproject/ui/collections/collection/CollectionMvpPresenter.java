package com.havrylyuk.thenounproject.ui.collections.collection;


import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface CollectionMvpPresenter<V extends CollectionMvpView> extends Presenter<V> {

    void loadCollections(int page);

    void loadCollections(String term);

}
