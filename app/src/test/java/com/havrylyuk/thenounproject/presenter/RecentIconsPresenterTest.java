package com.havrylyuk.thenounproject.presenter;

import com.havrylyuk.thenounproject.TestModels;
import com.havrylyuk.thenounproject.data.remote.helper.error.ServerNotAvailableException;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconMvpView;
import com.havrylyuk.thenounproject.ui.icons.recent.RecentPresenter;

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
public class RecentIconsPresenterTest extends
        BasePresenterTest<RecentPresenter<NounIconMvpView>, NounIconMvpView>{

    @Override
    RecentPresenter<NounIconMvpView> createPresenter() {
        return new RecentPresenter<>(compositeDisposableHelper, dataManager);
    }

    @Override
    NounIconMvpView createView() {
        return mock(NounIconMvpView.class);
    }

    @Test
    public void loadIconsSuccessfully() {
        RecentUploadResponse responseModel = TestModels.getRecentUploadIconsResponse(5);
        stubDataManagerLoadIcon(Observable.just(responseModel));

        presenter.loadRecentUploadIcons(1);
        testScheduler.triggerActions();

        verify(view, times(1)).showLoading();
        verify(view).showIcons(responseModel.getRecentUploads());
        verify(view, times(1)).hideLoading();
        verify(view, never()).showEmptyView();
        verify(view, never()).onError(anyString());
    }

    @Test
    public void loadIconsEmpty() {
        RecentUploadResponse responseModel = TestModels.getRecentUploadIconsResponse(0);
        stubDataManagerLoadIcon(Observable.just(responseModel));

        presenter.loadRecentUploadIcons(1);
        testScheduler.triggerActions();

        verify(view).showEmptyView();
        verify(view, never()).showIcons(ArgumentMatchers.<NounIcon>anyList());
        verify(view, never()).onError(anyString());
    }

    @Test
    public void loadIconsFail() {
        ServerNotAvailableException exception = new ServerNotAvailableException();

        when(dataManager.getErrorHandlerHelper()).thenReturn(errorHandlerHelper);
        when(errorHandlerHelper.getProperErrorMessage(exception)).thenReturn("Server not available");

        stubDataManagerLoadIcon(Observable.<RecentUploadResponse>error(exception));

        presenter.loadRecentUploadIcons(1);;
        testScheduler.triggerActions();

        verify(view).onError("Server not available");
        verify(view, never()).showIcons(ArgumentMatchers.<NounIcon>anyList());

    }

    private void stubDataManagerLoadIcon(Observable observable) {
        when(view.isNetworkConnected()).thenReturn(true);
        when(dataManager.getRecentUploadIcons(ArgumentMatchers.<String, String>anyMap())).thenReturn(observable);
    }

}
