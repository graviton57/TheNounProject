package com.havrylyuk.thenounproject.ui.icons.recent;

import com.havrylyuk.thenounproject.ui.base.Presenter;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconMvpView;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public interface RecentMvpPresenter <V extends NounIconMvpView> extends Presenter<V> {

    void loadRecentUploadIcons(int page);

}
