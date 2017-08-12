package com.havrylyuk.thenounproject.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Igor Havrylyuk on 15.06.2017.
 */
public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;

    @BindView(R.id.et_email)
    EditText emailEditText;

    @BindView(R.id.et_password)
    EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActivityFragmentComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.attachView(this);
    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClick(View v) {
        presenter.onServerLoginClick(emailEditText.getText().toString(),
                passwordEditText.getText().toString());
    }


    @OnClick(R.id.ib_fb_login)
    void onFbLoginClick(View v) {
        presenter.onFacebookLoginClick();
    }

    @Override
    public void openMainActivity() {
       /* Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);*/
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void init() {

    }

}
