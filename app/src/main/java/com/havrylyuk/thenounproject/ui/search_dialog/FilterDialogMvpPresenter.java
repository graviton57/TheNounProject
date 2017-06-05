package com.havrylyuk.thenounproject.ui.search_dialog;

import com.havrylyuk.thenounproject.ui.base.Presenter;

/**
 * Created by Igor Havrylyuk on 01.06.2017.
 */

public interface FilterDialogMvpPresenter<V extends FilterDialogMvpView> extends Presenter<V> {

    void onSubmitClick(boolean value);

    void loadFilterValue();
}
