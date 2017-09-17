package com.havrylyuk.thenounproject.ui.about;


import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.data.remote.model.response.UsageResponse;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class AboutPresenter <V extends AboutMvpView> extends BasePresenter<V>
        implements AboutMvpPresenter<V> {

    @Inject
    public AboutPresenter(CompositeDisposableHelper compositeDisposableHelper, DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void onCloseClick() {
        getMvpView().dismissDialog();
    }

    @Override
    public void getUsageLimit() {
        if (getMvpView().isNetworkConnected()){
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getOauthUsage()
                    .compose(getCompositeDisposableHelper().<UsageResponse>applySchedulers())
                    .subscribe(new Consumer<UsageResponse>() {
                        @Override
                        public void accept(UsageResponse usageResponse) throws Exception {
                            getMvpView().showUsageLimit(usageResponse);
                        }
                    }));
        }

    }
}
