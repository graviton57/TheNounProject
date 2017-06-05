package com.havrylyuk.thenounproject.data;


import com.havrylyuk.thenounproject.data.local.db.DbHelper;
import com.havrylyuk.thenounproject.data.local.preferences.PreferencesHelper;
import com.havrylyuk.thenounproject.data.remote.ApiHelper;

/**
 * Created by Igor Havrylyuk on 19.05.2017
 */

public interface DataManager extends PreferencesHelper, DbHelper, ApiHelper {


}
