package com.havrylyuk.thenounproject.ui.search_dialog;

import com.havrylyuk.thenounproject.ui.base.DialogMvpView;

/**
 * Created by Igor Havrylyuk on 01.06.2017.
 */

public interface FilterDialogMvpView extends DialogMvpView{

    void onFilterValueChange(boolean value);

    void showFilterValue(boolean value);

    void dismissDialog();
}
