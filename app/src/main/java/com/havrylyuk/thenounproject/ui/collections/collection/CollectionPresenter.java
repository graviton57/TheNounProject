package com.havrylyuk.thenounproject.ui.collections.collection;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.remote.AppApiHelper;
import com.havrylyuk.thenounproject.data.remote.helper.BaseViewSubscriber;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.response.CollectionsResponse;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class CollectionPresenter<V extends CollectionMvpView> extends BasePresenter<V>
    implements CollectionMvpPresenter<V> {

    @Inject
    public CollectionPresenter(CompositeDisposableHelper compositeDisposableHelper,
                               DataManager dataManager) {
        super(compositeDisposableHelper, dataManager);
    }

    @Override
    public void loadCollections(int page) {
        if (getMvpView().isNetworkConnected()) {
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getCollections(buildOptions(page))
                    .flatMap(new Function<CollectionsResponse, Observable<CollectionsResponse>>() {
                        @Override
                        public Observable<CollectionsResponse> apply(
                                final CollectionsResponse response) throws Exception {
                            return Observable.fromCallable(new Callable<CollectionsResponse>() {
                                @Override
                                public CollectionsResponse call() throws Exception {
                                    getDataManager().saveCollections(response.getNounCollections());
                                    return response;
                                }
                            });
                        }
                    })
                    .compose(getCompositeDisposableHelper().<CollectionsResponse>applySchedulers())
                    .subscribeWith(
                            new CollectionsObserver(getMvpView(), getDataManager().getErrorHandlerHelper())));
        } else {
            loadCollectionFromDb("");
        }
    }

    private void loadCollectionFromDb(String term) {
        if (term != null ) {
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getCollectionsFromDb(term)
                    .compose(getCompositeDisposableHelper().<List<NounCollection>>applySchedulers())
                    .subscribe(new Consumer<List<NounCollection>>() {
                        @Override
                        public void accept(List<NounCollection> list) throws Exception {
                            if (list.isEmpty()) {
                                getMvpView().showEmptyView();
                            } else {
                                getMvpView().showCollections(list);
                            }
                        }
                    }));
        } else {
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getCollectionsFromDb(null)
                    .compose(getCompositeDisposableHelper().<List<NounCollection>>applySchedulers())
                    .subscribe(new Consumer<List<NounCollection>>() {
                        @Override
                        public void accept(List<NounCollection> list) throws Exception {
                            if (!list.isEmpty()) {
                                getMvpView().showCollections(list);
                            } else {
                                getMvpView().showEmptyView();
                            }
                        }
                    }));
        }
    }

    @Override
    public void loadCollections(final String term) {
        if (getMvpView().isNetworkConnected()) {
        getCompositeDisposableHelper().addDisposable(getDataManager()
            .getCollections(term)
            .flatMap(new Function<CollectionsResponse, Observable<CollectionsResponse>>() {
                @Override
                public Observable<CollectionsResponse> apply(
                        final CollectionsResponse response) throws Exception {
                    return Observable.fromCallable(new Callable<CollectionsResponse>() {
                        @Override
                        public CollectionsResponse call() throws Exception {
                            getDataManager().saveCollections(response.getNounCollections());
                            getDataManager().saveSearchHistory(term, response.getNounCollections().get(0));
                            return response;
                        }
                    });
                }
            })
            .compose(getCompositeDisposableHelper().<CollectionsResponse>applySchedulers())
            .subscribeWith(
                    new CollectionsObserver(getMvpView(), getDataManager().getErrorHandlerHelper())));
        } else {
            loadCollectionFromDb(term);
        }
    }

    private HashMap<String, String> buildOptions(int page){
        HashMap<String, String> options = new HashMap<>();
        options.put(AppApiHelper.PAGE, String.valueOf(page));
        return options;
    }

    public class CollectionsObserver extends BaseViewSubscriber<CollectionMvpView, CollectionsResponse> {

        public CollectionsObserver(CollectionMvpView view, ErrorHandlerHelper errorHandlerHelper) {
            super(view, errorHandlerHelper);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getMvpView().showEmptyView();
        }

        @Override public void onNext(CollectionsResponse collectionsResponce) {
            super.onNext(collectionsResponce);
            if (collectionsResponce.getNounCollections().isEmpty()) {
                getMvpView().showEmptyView();
            } else {
                getMvpView().showCollections(collectionsResponce.getNounCollections());
                getMvpView().hideLoading();
            }
        }
    }
}
