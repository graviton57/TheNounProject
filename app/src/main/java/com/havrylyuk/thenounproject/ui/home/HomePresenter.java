package com.havrylyuk.thenounproject.ui.home;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.local.db.model.OrmHistory;
import com.havrylyuk.thenounproject.data.remote.AppApiHelper;
import com.havrylyuk.thenounproject.data.remote.helper.BaseViewSubscriber;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public class HomePresenter <V extends HomeMvpView> extends BasePresenter<V>
        implements HomeMvpPresenter<V> {

    private static final int CARD_RECENT_LIMIT = 8;

    @Inject
    public HomePresenter(CompositeDisposableHelper compositeDisposableHelper, DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void loadRecentUploadIcons() {
        Map<String, String> options = new HashMap<>();
        options.put(AppApiHelper.LIMIT, String.valueOf(CARD_RECENT_LIMIT));
        if (getMvpView().isNetworkConnected()){
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getRecentUploadIcons(options)
                    .compose(getCompositeDisposableHelper().<RecentUploadResponse>applySchedulers())
                    .subscribeWith(new HomeRecentObserver(getMvpView(),
                            getDataManager().getErrorHandlerHelper()))
            );
        } else {
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getIconsFromDb(null)
                    .compose(getCompositeDisposableHelper().<List<NounIcon>>applySchedulers())
                    .subscribe(new Consumer<List<NounIcon>>() {
                        @Override
                        public void accept(List<NounIcon> nounIcons) throws Exception {
                            if (!nounIcons.isEmpty()) {
                                getMvpView().showRecentUploads(nounIcons);
                            } else {
                                getMvpView().showEmptyRecentUpload();
                            }
                        }
                    }));
        }
    }

    @Override
    public void loadSearchHistory() {
        getCompositeDisposableHelper().addDisposable(getDataManager()
                .getSearchHistory()
                .compose(getCompositeDisposableHelper().<List<OrmHistory>>applySchedulers())
                .subscribe(new Consumer<List<OrmHistory>>() {
                    @Override
                    public void accept(List<OrmHistory> histories) throws Exception {
                        if (!histories.isEmpty()) {
                            getMvpView().showSearchHistory(histories);
                        } else {
                            getMvpView().showEmptySearchHistory();
                        }
                    }
                }));
    }

    public class HomeRecentObserver extends BaseViewSubscriber<HomeMvpView, RecentUploadResponse> {

        public HomeRecentObserver(HomeMvpView view, ErrorHandlerHelper errorHandlerHelper) {
            super(view, errorHandlerHelper);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getMvpView().showEmptyRecentUpload();
        }

        @Override
        public void onNext(RecentUploadResponse iconsResponse) {
            super.onNext(iconsResponse);
            if (iconsResponse.getRecentUploads().isEmpty()) {
                getMvpView().showEmptyRecentUpload();
            } else {
                getMvpView().showRecentUploads(iconsResponse.getRecentUploads());
            }
        }
    }
}
