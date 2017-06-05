package com.havrylyuk.thenounproject.ui.home;

import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public interface HomeMvpPresenter <V extends HomeMvpView> extends Presenter<V> {

    void loadRecentUploadIcons();

    void loadSearchHistory();
}
