package com.havrylyuk.thenounproject.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.ui.base.BaseActivity;

/**
 * Created by Igor Havrylyuk on 18.05.2017.
 */
public class AppUtils {

    /**
     * Check for internet connection
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Show loading progress
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();

        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.dialog_progress);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    /**
     * Hide keyboard
     */
    public static void hideKeyboard(Context context) {
        InputMethodManager inputManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (((Activity) context).getCurrentFocus() != null) {
            try {
                inputManager.hideSoftInputFromWindow(
                        ((BaseActivity) context).getCurrentFocus().getWindowToken(), 0);
            } catch (NullPointerException ex) {
                //
            }
        }
    }

}
