package com.havrylyuk.thenounproject.ui.login;


import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;
import com.havrylyuk.thenounproject.utils.CommonUtils;

import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 15.06.2017.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(CompositeDisposableHelper compositeDisposableHelper,
                          DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        //validate email and password
        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();
        getMvpView().hideLoading();
        getMvpView().openMainActivity();

    }

    @Override
    public void onFacebookLoginClick() {
        // instruct LoginActivity to initiate facebook login
        getMvpView().showLoading();
        getMvpView().hideLoading();
        getMvpView().openMainActivity();
    }
}
