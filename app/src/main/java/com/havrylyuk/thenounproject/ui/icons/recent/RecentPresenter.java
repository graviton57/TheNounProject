package com.havrylyuk.thenounproject.ui.icons.recent;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.AppApiHelper;
import com.havrylyuk.thenounproject.data.remote.helper.BaseViewSubscriber;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconMvpView;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public class RecentPresenter <V extends NounIconMvpView> extends BasePresenter<V>
        implements RecentMvpPresenter<V> {

    @Inject
    public RecentPresenter(CompositeDisposableHelper compositeDisposableHelper, DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void loadRecentUploadIcons(int page) {
        if (getMvpView().isNetworkConnected()){
            final HashMap<String, String> options = new HashMap<>();
            options.put(AppApiHelper.PAGE, String.valueOf(page));
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getRecentUploadIcons(options)
                    .flatMap(new Function<RecentUploadResponse, Observable<RecentUploadResponse>>() {
                        @Override
                        public Observable<RecentUploadResponse> apply(
                                final RecentUploadResponse response) throws Exception {
                            return Observable.fromCallable(new Callable<RecentUploadResponse>() {
                                @Override
                                public RecentUploadResponse call() throws Exception {
                                    getDataManager().saveIconsToDb(response.getRecentUploads());
                                    return response;
                                }
                            });
                        }
                    })
                    .compose(getCompositeDisposableHelper().<RecentUploadResponse>applySchedulers())
                    .subscribeWith(new RecentIconsObserver(getMvpView(),
                            getDataManager().getErrorHandlerHelper()))
            );
        } else {
            loadIconsFromDb(null);
        }
    }

    private void loadIconsFromDb(String term){
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getIconsFromDb(term)
                    .compose(getCompositeDisposableHelper().<List<NounIcon>>applySchedulers())
                    .subscribe(new Consumer<List<NounIcon>>() {
                        @Override
                        public void accept(List<NounIcon> nounIcons) throws Exception {
                            if (nounIcons.isEmpty()) {
                                getMvpView().showEmptyView();
                            } else {
                                getMvpView().showIcons(nounIcons);
                            }
                        }
                    }));
    }

    public  class RecentIconsObserver extends BaseViewSubscriber<NounIconMvpView, RecentUploadResponse> {

        public RecentIconsObserver(NounIconMvpView view, ErrorHandlerHelper errorHandlerHelper) {
            super(view, errorHandlerHelper);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getMvpView().showEmptyView();
        }

        @Override
        public void onNext(RecentUploadResponse iconsResponse) {
            super.onNext(iconsResponse);
            if (iconsResponse.getRecentUploads().isEmpty()) {
                getMvpView().showEmptyView();
            } else {
                getMvpView().showIcons(iconsResponse.getRecentUploads());
            }
        }
    }
}
