package com.havrylyuk.thenounproject.data;

import com.havrylyuk.thenounproject.TestModels;
import com.havrylyuk.thenounproject.data.remote.ApiInterface;
import com.havrylyuk.thenounproject.data.remote.AppApiHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.response.CollectionsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.UsageResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Igor Havrylyuk on 30.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApiHelperTest {

    @Mock
    ApiInterface apiInterface;
    @Mock
    ErrorHandlerHelper errorHandlerHelper;
    private AppApiHelper appApiHelper;
    private Map<String, String> options;

    @Before
    public void setUp() {
        appApiHelper = new AppApiHelper(apiInterface, errorHandlerHelper);
        options = new HashMap<>();
        options.put("page", "1");
    }

    @Test
    public void loadIcons() {
        IconsResponse responseModel = TestModels.getIconsResponseModel(5);

        when(apiInterface.getIcons(anyString(),  ArgumentMatchers.<String, String>anyMap()))
                .thenReturn(Observable.just(responseModel));
        TestObserver<IconsResponse> testSubscriber = new TestObserver<>();

        appApiHelper.getIcons("test", options).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertComplete();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(responseModel);
    }

    @Test
    public void loadRecentUploadsIcons() {
        RecentUploadResponse responseModel = TestModels.getRecentUploadIconsResponse(5);

        when(apiInterface.getRecentUploadsIcons(ArgumentMatchers.<String, String>anyMap()))
                .thenReturn( Observable.just(responseModel));
        TestObserver<RecentUploadResponse> testSubscriber = new TestObserver<>();

        appApiHelper.getRecentUploadIcons(options).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertComplete();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(responseModel);
    }

    @Test
    public void loadCollections() {
        CollectionsResponse responseModel = TestModels.getCollectionsResponse(5);

        when(apiInterface.getCollections(ArgumentMatchers.<String, String>anyMap()))
                .thenReturn( Observable.just(responseModel));
        TestObserver<CollectionsResponse> testSubscriber = new TestObserver<>();

        appApiHelper.getCollections(options).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertComplete();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(responseModel);
    }

    @Test
    public void loadCollectionIcons() {
        IconsResponse responseModel = TestModels.getIconsResponseModel(5);

        when(apiInterface.getCollectionIcons(anyInt(), ArgumentMatchers.<String, String>anyMap()))
                .thenReturn(Observable.just(responseModel));
        TestObserver<IconsResponse> testSubscriber = new TestObserver<>();

        appApiHelper.getCollectionIcons(29508, options).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertComplete();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(responseModel);
    }

    @Test
    public void loadUsageLimit() {
        UsageResponse responseModel = TestModels.getOauthUsage();

        when(apiInterface.getOauthUsage()).thenReturn(Observable.just(responseModel));
        TestObserver<UsageResponse> testSubscriber = new TestObserver<>();

        appApiHelper.getOauthUsage().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertComplete();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(responseModel);
    }
}
