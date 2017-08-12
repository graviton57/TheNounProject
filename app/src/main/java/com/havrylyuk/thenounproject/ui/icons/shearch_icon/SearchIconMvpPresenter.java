package com.havrylyuk.thenounproject.ui.icons.shearch_icon;

import com.havrylyuk.thenounproject.ui.base.Presenter;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconMvpView;


/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface SearchIconMvpPresenter<V extends NounIconMvpView> extends Presenter<V> {

    void loadRequestedIcons(String term, int page, boolean isPublicOnly);

}
