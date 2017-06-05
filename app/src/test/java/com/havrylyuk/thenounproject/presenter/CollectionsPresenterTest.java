package com.havrylyuk.thenounproject.presenter;

import com.havrylyuk.thenounproject.TestModels;
import com.havrylyuk.thenounproject.data.remote.helper.error.ServerNotAvailableException;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.data.remote.model.response.CollectionsResponse;
import com.havrylyuk.thenounproject.ui.collections.collection.CollectionMvpView;
import com.havrylyuk.thenounproject.ui.collections.collection.CollectionPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

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
public class CollectionsPresenterTest extends
        BasePresenterTest<CollectionPresenter<CollectionMvpView>, CollectionMvpView> {

    @Override
    CollectionPresenter<CollectionMvpView> createPresenter() {
        return new CollectionPresenter<>(compositeDisposableHelper, dataManager);
    }

    @Override
    CollectionMvpView createView() {
        return mock(CollectionMvpView.class);
    }

    @Test
    public void loadCollectionsSuccessfully() {
        CollectionsResponse responseModel = TestModels.getCollectionsResponse(5);
        stubDataManagerLoadCollection(Observable.just(responseModel));

        presenter.loadCollections(1);
        testScheduler.triggerActions();

        verify(view, times(1)).showLoading();
        verify(view).showCollections(responseModel.getNounCollections());
        verify(view, times(2)).hideLoading();
        verify(view, never()).showEmptyView();
        verify(view, never()).onError(anyString());
    }

    @Test
    public void loadCollectionsEmpty() {
        CollectionsResponse responseModel = TestModels.getCollectionsResponse(0);
        stubDataManagerLoadCollection(Observable.just(responseModel));

        presenter.loadCollections(1);
        testScheduler.triggerActions();

        verify(view).showEmptyView();
        verify(view, never()).showCollections(ArgumentMatchers.<NounCollection>anyList());
        verify(view, never()).onError(anyString());
    }

    @Test
    public void loadCollectionsFail() {
        ServerNotAvailableException exception = new ServerNotAvailableException();

        when(dataManager.getErrorHandlerHelper()).thenReturn(errorHandlerHelper);
        when(errorHandlerHelper.getProperErrorMessage(exception)).thenReturn("Server not available");

        stubDataManagerLoadCollection(Observable.<CollectionsResponse>error(exception));

        presenter.loadCollections(1);
        testScheduler.triggerActions();

        verify(view).onError("Server not available");
        verify(view, never()).showCollections(ArgumentMatchers.<NounCollection>anyList());

    }

    private void stubDataManagerLoadCollection(Observable observable) {
        when(view.isNetworkConnected()).thenReturn(true);
        when(dataManager.getCollections(ArgumentMatchers.<String, String>anyMap())).thenReturn(observable);
    }
}
