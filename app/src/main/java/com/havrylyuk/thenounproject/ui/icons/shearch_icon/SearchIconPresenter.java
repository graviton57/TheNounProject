package com.havrylyuk.thenounproject.ui.icons.shearch_icon;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.AppApiHelper;
import com.havrylyuk.thenounproject.data.remote.helper.BaseViewSubscriber;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconMvpView;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 *
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class SearchIconPresenter<V extends NounIconMvpView> extends BasePresenter<V>
    implements SearchIconMvpPresenter<V> {

    @Inject
    public SearchIconPresenter(CompositeDisposableHelper compositeDisposableHelper,
                               DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    private void loadIconsFromDb(String term){
         getCompositeDisposableHelper().addDisposable(getDataManager()
                   .getIconsFromDb(term)
                    .compose(getCompositeDisposableHelper().<List<NounIcon>>applySchedulers())
                    .subscribe(new Consumer<List<NounIcon>>() {
                        @Override
                        public void accept(List<NounIcon> nounIcons) throws Exception {
                            if (!nounIcons.isEmpty()) {
                                getMvpView().showIcons(nounIcons);
                            } else {
                                getMvpView().showEmptyView();
                            }
                        }
                    }));
    }

    @Override
    public void loadRequestedIcons(final String term, int page, boolean isPublicOnly) {
        if (getMvpView().isNetworkConnected()){
            HashMap<String, String> options = new HashMap<>();
            options.put(AppApiHelper.PAGE, String.valueOf(page));
            if (isPublicOnly){
                options.put(AppApiHelper.IS_PUBLIC, "1");
            }
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getIcons(term ,options)
                    .flatMap(new Function<IconsResponse, Observable<IconsResponse>>() {
                        @Override
                        public Observable<IconsResponse> apply(
                                final IconsResponse response) throws Exception {
                            return Observable.fromCallable(new Callable<IconsResponse>() {
                                @Override
                                public IconsResponse call() throws Exception {
                                    getDataManager().saveIconsToDb(response.getIcons());
                                    getDataManager().saveSearchHistory(term, response.getIcons().get(0));
                                    return response;
                                }
                            });
                        }
                    })
                    .compose(getCompositeDisposableHelper().<IconsResponse>applySchedulers())
                    .subscribeWith(new IconsObserver(getMvpView(),
                                    getDataManager().getErrorHandlerHelper())));
        } else {
            getMvpView().showEmptyView();
            Timber.d("no network - load recent uploads fom db");
            loadIconsFromDb(term);
       }
    }

    public class IconsObserver extends BaseViewSubscriber<NounIconMvpView, IconsResponse> {

        public IconsObserver(NounIconMvpView view, ErrorHandlerHelper errorHandlerHelper) {
            super(view, errorHandlerHelper);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getMvpView().showEmptyView();
        }

        @Override
        public void onNext(IconsResponse iconsResponse) {
            super.onNext(iconsResponse);
            if (iconsResponse.getIcons().isEmpty()) {
                getMvpView().showEmptyView();
            } else {
                getMvpView().showIcons(iconsResponse.getIcons());
            }
        }
    }

}
