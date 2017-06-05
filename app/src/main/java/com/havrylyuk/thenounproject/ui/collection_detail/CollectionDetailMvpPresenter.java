package com.havrylyuk.thenounproject.ui.collection_detail;


import com.havrylyuk.thenounproject.ui.base.BaseMvpView;
import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface CollectionDetailMvpPresenter<V extends BaseMvpView> extends Presenter<V> {

    void getCollectionIconsById(String id);

}
