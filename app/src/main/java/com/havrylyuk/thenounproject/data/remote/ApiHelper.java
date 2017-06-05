package com.havrylyuk.thenounproject.data.remote;

import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.response.CollectionsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.UsageResponse;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */

public interface ApiHelper {

    Observable<CollectionsResponse> getCollections(Map<String, String> options);

    Observable<CollectionsResponse> getCollections(String term);

    Observable<IconsResponse> getIcons(String term, Map<String, String> options);

    Observable<IconsResponse> getCollectionIcons(int collectionId, Map<String, String> options);

    Observable<RecentUploadResponse> getRecentUploadIcons(Map<String, String> options);

    Observable<UsageResponse> getOauthUsage();

    ErrorHandlerHelper getErrorHandlerHelper();

    void setErrorHandler(ErrorHandlerHelper errorHandler);

}
