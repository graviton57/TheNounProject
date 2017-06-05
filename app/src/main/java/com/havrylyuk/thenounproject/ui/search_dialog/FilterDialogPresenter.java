package com.havrylyuk.thenounproject.ui.search_dialog;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Igor Havrylyuk on 01.06.2017.
 */

public class FilterDialogPresenter<V extends FilterDialogMvpView> extends BasePresenter<V>
        implements FilterDialogMvpPresenter<V> {

    @Inject
    public FilterDialogPresenter(CompositeDisposableHelper compositeDisposableHelper,
                                 DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void onSubmitClick(boolean value) {
        Timber.i("onSubmitClick value=%s", value);
        getDataManager().setPublicIcons(value);
        getMvpView().onFilterValueChange(value);
        getMvpView().dismissDialog();

    }

    @Override
    public void loadFilterValue() {
        boolean value = getDataManager().isPublicIcons();
        Timber.i("loadFilterValue value=%s", value);
        getMvpView().showFilterValue(value);
    }
}
