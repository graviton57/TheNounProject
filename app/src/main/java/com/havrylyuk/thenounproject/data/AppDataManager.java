package com.havrylyuk.thenounproject.data;

import android.content.Context;

import com.havrylyuk.thenounproject.data.local.db.model.OrmCollection;
import com.havrylyuk.thenounproject.data.local.db.DbHelper;
import com.havrylyuk.thenounproject.data.local.db.model.OrmHistory;
import com.havrylyuk.thenounproject.data.local.db.model.OrmIcon;
import com.havrylyuk.thenounproject.data.local.preferences.PreferencesHelper;
import com.havrylyuk.thenounproject.data.remote.ApiHelper;
import com.havrylyuk.thenounproject.data.remote.helper.error.ErrorHandlerHelper;
import com.havrylyuk.thenounproject.data.remote.model.LoginRequest;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.data.remote.model.response.CollectionsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.IconsResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.LoginResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.RecentUploadResponse;
import com.havrylyuk.thenounproject.data.remote.model.response.UsageResponse;

import io.reactivex.Observable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */

public class AppDataManager implements DataManager {

    private final Context context;
    private final DbHelper dbHelper;
    private final PreferencesHelper applicationPreferences;
    private final ApiHelper apiHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper,
                          PreferencesHelper applicationPreferences, ApiHelper apiHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
        this.applicationPreferences = applicationPreferences;
        this.apiHelper = apiHelper;
    }

    public Context getContext() {
        return context;
    }

    // Preferences

    @Override
    public void setLoggedIn() {
        applicationPreferences.setLoggedIn();
    }

    @Override
    public boolean isLoggedIn() {
        return applicationPreferences.isLoggedIn();
    }

    @Override
    public void setUserName(String userName) {
        applicationPreferences.setUserName(userName);
    }

    @Override
    public String getUserName() {
        return applicationPreferences.getUserName();
    }

    @Override
    public Boolean isPublicIcons() {
        return applicationPreferences.isPublicIcons();
    }

    @Override
    public void setPublicIcons(boolean isPublicDomain) {
         applicationPreferences.setPublicIcons(isPublicDomain);
    }

    // SQLite Database
    @Override
    public Boolean saveCollections(List<NounCollection> collections) {
        return dbHelper.saveCollections(collections);
    }

    @Override
    public Observable<List<OrmCollection>> getAllCollections() {
        return dbHelper.getAllCollections();
    }

    @Override
    public Observable<List<NounCollection>> getCollectionsFromDb(String term) {
        return dbHelper.getCollectionsFromDb(term);
    }

    @Override
    public Observable<Boolean> deleteCollection() {
        return dbHelper.deleteCollection();
    }

    @Override
    public Boolean saveIconsToDb(List<NounIcon> iconsList) {
        return dbHelper.saveIconsToDb(iconsList);
    }

    @Override
    public Observable<List<NounIcon>> getIconsFromDb(String term) {
        return dbHelper.getIconsFromDb(term);
    }

    @Override
    public Observable<List<NounIcon>> getFavoriteIcons() {
        return dbHelper.getFavoriteIcons();
    }

    @Override
    public Observable<OrmIcon> getIconById(String id) {
        return dbHelper.getIconById(id);
    }

    @Override
    public Observable<List<OrmIcon>> getIconByTerm(String term) {
        return dbHelper.getIconByTerm(term);
    }

    @Override
    public Observable<Boolean> deleteIcons() {
        return dbHelper.deleteIcons();
    }

    @Override
    public Observable<List<OrmHistory>> getSearchHistory() {
        return dbHelper.getSearchHistory();
    }

    @Override
    public <E> Long saveSearchHistory(String query, E nounEntity) {
        return dbHelper.saveSearchHistory(query, nounEntity);
    }

    @Override
    public Observable<Boolean> clearSearchHistory() {
        return dbHelper.clearSearchHistory();
    }

    @Override
    public Long getIconsCount() {
        return dbHelper.getIconsCount();
    }

    @Override
    public Long getCollectionsCount() {
        return dbHelper.getCollectionsCount();
    }

    @Override
    public Long getHistoryCount() {
        return dbHelper.getHistoryCount();
    }

    @Override
    public Observable<OrmCollection> getCollectionById(String id) {
        return dbHelper.getCollectionById(id);
    }

    @Override
    public Observable<List<OrmCollection>> getCollectionsByTerm(String term) {
        return dbHelper.getCollectionsByTerm(term);
    }

    @Override
    public Observable<List<NounIcon>> getCollectionIcons(String collectionId) {
        return dbHelper.getCollectionIcons(collectionId);
    }

    @Override
    public Observable<CollectionsResponse> getCollections(Map<String, String> options) {
        return apiHelper.getCollections(options);
    }

    @Override
    public Observable<CollectionsResponse> getCollections(String term) {
        return apiHelper.getCollections(term);
    }

    // Api

    @Override
    public Observable<IconsResponse> getIcons(String term, Map<String, String> options) {
        return apiHelper.getIcons(term, options);
    }

    @Override
    public Observable<IconsResponse> getCollectionIcons(int collectionId, Map<String, String> options) {
        return apiHelper.getCollectionIcons(collectionId, options);
    }

    @Override
    public Observable<RecentUploadResponse> getRecentUploadIcons(Map<String, String> options) {
        return apiHelper.getRecentUploadIcons(options);
    }

    @Override
    public Observable<UsageResponse> getOauthUsage() {
        return apiHelper.getOauthUsage();
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return apiHelper.doServerLoginApiCall(request);
    }

    @Override
    public Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return apiHelper.doFacebookLoginApiCall(request);
    }

    @Override
    public ErrorHandlerHelper getErrorHandlerHelper() {
        return apiHelper.getErrorHandlerHelper();
    }

    @Override
    public void setErrorHandler(ErrorHandlerHelper errorHandler) {
        apiHelper.setErrorHandler(errorHandler);
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        //
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null);
    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, LoggedInMode loggedInMode,
                               String userName, String email, String profilePicPath) {
        //set Access Token(accessToken);
        //set Current User Id(userId);
        //set Current User Logged In Mode(loggedInMode);
        //set Current User Name(userName);
        //set Current User Email(email);
        //set Current User Profile Picture Url (profilePicPath);
         updateApiHeader(userId, accessToken);
    }
}
