package com.havrylyuk.thenounproject.ui.icons;

import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public interface IconsMvpPresenter<V extends IconsMvpView> extends Presenter<V> {

    void setActivityTitle(String title);
}
