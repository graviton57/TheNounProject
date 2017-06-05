package com.havrylyuk.thenounproject.ui.home;

import com.havrylyuk.thenounproject.data.local.db.model.OrmHistory;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.ui.base.BaseMvpView;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public interface HomeMvpView extends BaseMvpView {

    void showEmptyRecentUpload();

    void showEmptySearchHistory();

    void showRecentUploads(List<NounIcon> list);

    void showSearchHistory(List<OrmHistory> list);

}
