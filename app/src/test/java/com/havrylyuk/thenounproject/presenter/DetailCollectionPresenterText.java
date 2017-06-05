package com.havrylyuk.thenounproject.presenter;


import com.havrylyuk.thenounproject.TestModels;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.ui.collection_detail.CollectionDetailMvpView;
import com.havrylyuk.thenounproject.ui.collection_detail.CollectionDetailPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Igor Havrylyuk on 30.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailCollectionPresenterText
        extends BasePresenterTest<CollectionDetailPresenter<CollectionDetailMvpView>, CollectionDetailMvpView> {

    @Override
    CollectionDetailPresenter<CollectionDetailMvpView> createPresenter() {
        return new CollectionDetailPresenter<>(compositeDisposableHelper, dataManager);
    }

    @Override
    CollectionDetailMvpView createView() {
        return mock(CollectionDetailMvpView.class);
    }

    @Test
    public void loadIconsSuccessfully() {
        IconsResponse responseModel = TestModels.getCollectionIconsResponse(5);
        stubDataManagerLoadIcons(Observable.just(responseModel));

        presenter.getCollectionIconsById("29508");
        testScheduler.triggerActions();

        verify(view, times(1)).showLoading();
        verify(view).showIcons(responseModel.getIcons());
        verify(view, times(1)).hideLoading();
        verify(view, never()).showEmptyView();
        verify(view, never()).onError(anyString());
    }

    @Test
    public void loadCollectionIconsEmpty() {
        IconsResponse responseModel = TestModels.getCollectionIconsResponse(0);
        stubDataManagerLoadIcons(Observable.just(responseModel));

        presenter.getCollectionIconsById("29508");
        testScheduler.triggerActions();

        verify(view).showEmptyView();
        verify(view, never()).showIcons(ArgumentMatchers.<NounIcon>anyList());
        verify(view, never()).onError(anyString());
    }

    private void stubDataManagerLoadIcons(Observable observable) {
        when(view.isNetworkConnected()).thenReturn(true);
        when(dataManager.getCollectionIcons(anyInt(), ArgumentMatchers.<String, String>anyMap())).thenReturn(observable);
    }
}
