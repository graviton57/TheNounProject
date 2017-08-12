package com.havrylyuk.thenounproject.data;


import com.havrylyuk.thenounproject.data.local.db.DbHelper;
import com.havrylyuk.thenounproject.data.local.preferences.PreferencesHelper;
import com.havrylyuk.thenounproject.data.remote.ApiHelper;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */

public interface DataManager extends PreferencesHelper, DbHelper, ApiHelper {

    void updateApiHeader(Long userId, String accessToken);

    void setUserAsLoggedOut();

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_FB(1),
        LOGGED_IN_MODE_SERVER(2);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }

}
