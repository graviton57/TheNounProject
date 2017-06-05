package com.havrylyuk.thenounproject.ui.collection_detail;

import com.havrylyuk.thenounproject.data.DataManager;
import com.havrylyuk.thenounproject.data.local.db.model.OrmIcon;
import com.havrylyuk.thenounproject.data.remote.helper.BaseViewSubscriber;
import com.havrylyuk.thenounproject.data.remote.helper.CompositeDisposableHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.ui.base.BasePresenter;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class CollectionDetailPresenter<V extends CollectionDetailMvpView> extends BasePresenter<V>
    implements CollectionDetailMvpPresenter<V> {

    @Inject
    public CollectionDetailPresenter(CompositeDisposableHelper compositeDisposableHelper,
                                   DataManager dataManager) {
      super(compositeDisposableHelper, dataManager);
    }


    @Override
    public void getCollectionIconsById(String id) {
        if (getMvpView().isNetworkConnected()){
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getCollectionIcons(Integer.parseInt(id) , new HashMap<String, String>())
                    .compose(getCompositeDisposableHelper().<IconsResponse>applySchedulers())
                    .subscribeWith(
                            new IconsObserver(getMvpView(), getDataManager().getErrorHandlerHelper())));
        } else {
            getCompositeDisposableHelper().addDisposable(getDataManager()
                    .getCollectionIcons(id)
                    .compose(getCompositeDisposableHelper().<List<NounIcon>>applySchedulers())
                    .subscribe(new Consumer<List<NounIcon>>() {
                        @Override public void accept(List<NounIcon> icons) throws Exception {
                            if (icons != null && icons.size() > 0) {
                                getMvpView().showIcons(icons);
                            } else {
                                getMvpView().showEmptyView();
                            }
                        }
                    }));
        }
    }

    public class IconsObserver extends BaseViewSubscriber<CollectionDetailMvpView, IconsResponse> {

    public IconsObserver(CollectionDetailMvpView view, ErrorHandlerHelper errorHandlerHelper) {
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
