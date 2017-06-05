package com.havrylyuk.thenounproject.data;

import com.havrylyuk.thenounproject.BuildConfig;
import com.havrylyuk.thenounproject.TestModels;
import com.havrylyuk.thenounproject.data.local.db.AppDbHelper;
import com.havrylyuk.thenounproject.data.local.db.model.DaoMaster;
import com.havrylyuk.thenounproject.data.local.db.model.DaoSession;
import com.havrylyuk.thenounproject.data.local.db.model.OrmCollection;
import com.havrylyuk.thenounproject.data.local.db.model.OrmHistory;
import com.havrylyuk.thenounproject.data.local.db.model.OrmIcon;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Igor Havrylyuk on 30.05.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class DbHelperTest {

    private AppDbHelper dbHelper;
    private DaoSession daoSession;

    @Before
    public void setUp() {
        daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(RuntimeEnvironment.application,
                "test").getWritableDb()).newSession();

        dbHelper = new AppDbHelper(daoSession);
    }

    @Test
    public void addHistoryAndDelete() {
        dbHelper.saveSearchHistory("test", TestModels.newNounIconModel());
        assertEquals(1, dbHelper.getHistoryCount().intValue());
        dbHelper.clearSearchHistory().subscribe();
        assertEquals(0, dbHelper.getHistoryCount().intValue());
    }

    @Test
    public void addIconsAndDelete() {
        dbHelper.saveIconsToDb(TestModels.getDbIcons(50));
        assertEquals(50, dbHelper.getIconsCount().intValue());
        dbHelper.deleteIcons().subscribe();
        assertEquals(0, dbHelper.getIconsCount().intValue());
    }

    @Test
    public void addCollectionsAndDelete() {
        dbHelper.saveCollections(TestModels.getDbCollections(50));
        assertEquals(50, dbHelper.getCollectionsCount().intValue());
        dbHelper.deleteCollection().subscribe();
        assertEquals(0, dbHelper.getCollectionsCount().intValue());
    }

    @Test
    public void listHistory() {
        TestObserver<List<OrmHistory>> listTestSubscriber = new TestObserver<>();
        dbHelper.getSearchHistory().subscribe(listTestSubscriber);
        listTestSubscriber.assertNoErrors();
        listTestSubscriber.assertComplete();
        listTestSubscriber.assertValueCount(1);
        listTestSubscriber.assertValue(new ArrayList<OrmHistory>());
    }


    @Test
    public void listIcons() {
        TestObserver<List<NounIcon>> listTestSubscriber = new TestObserver<>();
        dbHelper.getIconsFromDb(null).subscribe(listTestSubscriber);
        listTestSubscriber.assertNoErrors();
        listTestSubscriber.assertComplete();
        listTestSubscriber.assertValueCount(1);
        listTestSubscriber.assertValue(new ArrayList<NounIcon>());
    }

    @Test
    public void listCollections() {
        TestObserver<List<OrmCollection>> listTestSubscriber = new TestObserver<>();
        dbHelper.getAllCollections().subscribe(listTestSubscriber);
        listTestSubscriber.assertNoErrors();
        listTestSubscriber.assertComplete();
        listTestSubscriber.assertValueCount(1);
        listTestSubscriber.assertValue(new ArrayList<OrmCollection>());
    }

    @Test
    public void addIcons() {
        dbHelper.saveIconsToDb(TestModels.getDbIcons(50));
        // check if the icons is added
        assertEquals(50, dbHelper.getIconsCount().intValue());
        TestObserver<OrmIcon> testSubscriber = new TestObserver<>();
        dbHelper.getIconById("1").subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertComplete();
    }

}
