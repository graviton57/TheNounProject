package com.havrylyuk.thenounproject.data.remote;

import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.response.CollectionsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.UsageResponse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */

public class AppApiHelper implements ApiHelper {


    private ApiInterface apiInterface;
    private ErrorHandlerHelper errorHandlerHelper;

    public static final int START_PAGE = 1;
    public final static String PAGE = "page";
    public final static String IS_PUBLIC = "limit_to_public_domain";
    public final static String LIMIT = "limit";
    public final static String OFFSET = "offset";


    @Inject
    public AppApiHelper(ApiInterface apiInterface, ErrorHandlerHelper errorHandlerHelper) {
        this.apiInterface = apiInterface;
        this.errorHandlerHelper = errorHandlerHelper;
    }

    @Override
    public ErrorHandlerHelper getErrorHandlerHelper() {
        return errorHandlerHelper;
    }

    @Override
    public void setErrorHandler(ErrorHandlerHelper errorHandler) {
        this.errorHandlerHelper = errorHandler;
    }

    @Override
    public Observable<CollectionsResponse> getCollections(Map<String, String> options) {
        return apiInterface.getCollections(checkOptions(options));
    }

    @Override
    public Observable<CollectionsResponse> getCollections(String term) {
        return apiInterface.getCollections(term);
    }

    @Override
    public Observable<IconsResponse> getIcons(String term, Map<String, String> options) {
        return apiInterface.getIcons(term, options);
    }

    @Override
    public Observable<IconsResponse> getCollectionIcons(int collectionId, Map<String, String> options ) {
        return apiInterface.getCollectionIcons(collectionId, checkOptions(options));
    }

    @Override
    public Observable<RecentUploadResponse> getRecentUploadIcons(Map<String, String> options) {
        return apiInterface.getRecentUploadsIcons(options);
    }

    @Override
    public Observable<UsageResponse> getOauthUsage() {
        return apiInterface.getOauthUsage();
    }


    /*
     * Check options
     * if options == null get first page from api (default 50 items)
     */
    private Map<String, String> checkOptions(Map<String, String> options){
        if (options == null) {
            options = new HashMap<>();
            options.put(PAGE, String.valueOf(START_PAGE));
        }
        return options;
    }
}
