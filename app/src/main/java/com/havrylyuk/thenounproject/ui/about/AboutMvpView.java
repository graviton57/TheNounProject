package com.havrylyuk.thenounproject.ui.about;


import com.havrylyuk.thenounproject.data.remote.model.response.UsageResponse;
import com.havrylyuk.thenounproject.ui.base.DialogMvpView;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface AboutMvpView extends DialogMvpView {

    void dismissDialog();

    void showUsageLimit(UsageResponse response);
}
