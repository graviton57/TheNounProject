package com.havrylyuk.thenounproject.ui.main;

import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 21.05.2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends Presenter<V> {

    void setUserInfo();

    void setCurrentTitle(String title);


}
