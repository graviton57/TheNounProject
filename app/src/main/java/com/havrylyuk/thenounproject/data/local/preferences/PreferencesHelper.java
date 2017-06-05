package com.havrylyuk.thenounproject.data.local.preferences;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface PreferencesHelper {

    void setLoggedIn();

    boolean isLoggedIn();

    void setUserName(String userName);

    String getUserName();

    Boolean isPublicIcons();

    void setPublicIcons(boolean isPublicDomain);

}
