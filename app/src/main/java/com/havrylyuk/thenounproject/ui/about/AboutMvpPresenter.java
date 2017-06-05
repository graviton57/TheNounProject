package com.havrylyuk.thenounproject.ui.about;


import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface AboutMvpPresenter <V extends AboutMvpView> extends Presenter<V> {

    void onCloseClick();

}
