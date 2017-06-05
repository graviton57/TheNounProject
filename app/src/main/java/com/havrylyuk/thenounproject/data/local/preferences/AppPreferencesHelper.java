package com.havrylyuk.thenounproject.data.local.preferences;

import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_IS_LOGGED_IN = "PREF_KEY_IS_LOGGED_IN";
    private static final String PREF_KEY_IS_PUBLIC_DOMAIN = "PREF_KEY_IS_PUBLIC_DOMAIN";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME_VALUE";

    private CommonPreferencesHelper commonPreferencesHelper;

    @Inject
    public AppPreferencesHelper(CommonPreferencesHelper commonPreferencesHelper) {
        this.commonPreferencesHelper = commonPreferencesHelper;
    }

    @Override
    public void setLoggedIn() {
        commonPreferencesHelper.setBooleanToPrefs(PREF_KEY_IS_LOGGED_IN, true);
    }

    @Override
    public boolean isLoggedIn() {
        return commonPreferencesHelper.getBooleanFromPrefs(PREF_KEY_IS_LOGGED_IN);
    }

    @Override
    public void setUserName(String userName) {
        commonPreferencesHelper.setStringToPrefs(PREF_KEY_USER_NAME, userName);
    }

    @Override
    public String getUserName() {
        return commonPreferencesHelper.getStringFromPrefs(PREF_KEY_USER_NAME);
    }

    @Override
    public Boolean isPublicIcons() {
        return commonPreferencesHelper.getBooleanFromPrefs(PREF_KEY_IS_PUBLIC_DOMAIN);
    }

    @Override
    public void setPublicIcons(boolean isPublic) {
        commonPreferencesHelper.setBooleanToPrefs(PREF_KEY_IS_PUBLIC_DOMAIN, isPublic);
    }
}
