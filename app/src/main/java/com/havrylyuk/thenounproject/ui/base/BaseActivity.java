package com.havrylyuk.thenounproject.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.havrylyuk.thenounproject.TheNounProjectApp;
import com.havrylyuk.thenounproject.injection.component.ActivityFragmentComponent;
import com.havrylyuk.thenounproject.injection.component.DaggerActivityFragmentComponent;
import com.havrylyuk.thenounproject.injection.module.ActivityFragmentModule;
import com.havrylyuk.thenounproject.utils.AppUtils;

import butterknife.Unbinder;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements BaseMvpView, BaseFragment.Callback  {

    private ActivityFragmentComponent activityFragmentComponent;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFragmentComponent = DaggerActivityFragmentComponent.builder()
                .activityFragmentModule(new ActivityFragmentModule(this))
                .applicationComponent(TheNounProjectApp.getApplicationComponent())
                .build();
    }

    public ActivityFragmentComponent getActivityFragmentComponent() {
        return activityFragmentComponent;
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = AppUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isNetworkConnected() {
        return AppUtils.isNetworkAvailable(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {
        AppUtils.hideKeyboard(this);
    }

    public void setUnBinder(Unbinder unBinder) {
        unbinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    protected abstract void init();

}
