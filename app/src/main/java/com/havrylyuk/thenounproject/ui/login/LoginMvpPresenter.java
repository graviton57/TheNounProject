package com.havrylyuk.thenounproject.ui.login;


import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 15.06.2017.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends Presenter<V> {

    void onServerLoginClick(String email, String password);

    void onFacebookLoginClick();

}
