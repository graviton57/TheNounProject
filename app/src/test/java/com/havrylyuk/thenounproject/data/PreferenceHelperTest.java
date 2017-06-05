package com.havrylyuk.thenounproject.data;

import com.havrylyuk.thenounproject.BuildConfig;
import com.havrylyuk.thenounproject.data.local.preferences.AppPreferencesHelper;
import com.havrylyuk.thenounproject.data.local.preferences.CommonPreferencesHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Igor Havrylyuk on 30.05.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class PreferenceHelperTest {

    private AppPreferencesHelper appPreferencesHelper;

    @Before
    public void setUp(){
        CommonPreferencesHelper commonPreferencesHelper = new CommonPreferencesHelper(
                RuntimeEnvironment.application);
        appPreferencesHelper = new AppPreferencesHelper(commonPreferencesHelper);
    }

    @Test
    public void putAndGetUserName(){
        String userName = "Graviton57";
        appPreferencesHelper.setUserName(userName);
        assertEquals(userName, appPreferencesHelper.getUserName());
    }

    @Test
    public void putAndGetPublicIcons(){
        appPreferencesHelper.setPublicIcons(true);
        assertTrue(appPreferencesHelper.isPublicIcons());
    }

    @Test
    public void putAndGetLoginInfo(){
        appPreferencesHelper.setLoggedIn();
        assertTrue(appPreferencesHelper.isLoggedIn());
    }

}
