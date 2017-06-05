package com.havrylyuk.thenounproject.injection.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.havrylyuk.thenounproject.injection.qualifier.ActivityContext;
import com.havrylyuk.thenounproject.injection.scope.ActivityScope;
import com.havrylyuk.thenounproject.ui.about.AboutMvpPresenter;
import com.havrylyuk.thenounproject.ui.about.AboutMvpView;
import com.havrylyuk.thenounproject.ui.about.AboutPresenter;
import com.havrylyuk.thenounproject.ui.collection_detail.CollectionDetailMvpPresenter;
import com.havrylyuk.thenounproject.ui.collection_detail.CollectionDetailMvpView;
import com.havrylyuk.thenounproject.ui.collection_detail.CollectionDetailPresenter;
import com.havrylyuk.thenounproject.ui.collections.CollectionsMvpPresenter;
import com.havrylyuk.thenounproject.ui.collections.CollectionsMvpView;
import com.havrylyuk.thenounproject.ui.collections.CollectionsPresenter;
import com.havrylyuk.thenounproject.ui.collections.collection.CollectionMvpPresenter;
import com.havrylyuk.thenounproject.ui.collections.collection.CollectionMvpView;
import com.havrylyuk.thenounproject.ui.collections.collection.CollectionPresenter;
import com.havrylyuk.thenounproject.ui.collections.collection.CollectionAdapter;
import com.havrylyuk.thenounproject.ui.home.HomeMvpPresenter;
import com.havrylyuk.thenounproject.ui.home.HomeMvpView;
import com.havrylyuk.thenounproject.ui.home.HomePresenter;
import com.havrylyuk.thenounproject.ui.home.RecentCardAdapter;
import com.havrylyuk.thenounproject.ui.home.SearchHistoryAdapter;
import com.havrylyuk.thenounproject.ui.icons.IconsMvpPresenter;
import com.havrylyuk.thenounproject.ui.icons.IconsMvpView;
import com.havrylyuk.thenounproject.ui.icons.IconsPagerAdapter;
import com.havrylyuk.thenounproject.ui.icons.IconsPresenter;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconAdapter;
import com.havrylyuk.thenounproject.ui.icons.recent.RecentIconAdapter;
import com.havrylyuk.thenounproject.ui.icons.shearch_icon.SearchIconMvpPresenter;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconMvpView;
import com.havrylyuk.thenounproject.ui.icons.shearch_icon.SearchIconPresenter;
import com.havrylyuk.thenounproject.ui.icons.recent.RecentMvpPresenter;
import com.havrylyuk.thenounproject.ui.icons.recent.RecentPresenter;
import com.havrylyuk.thenounproject.ui.main.MainMvpPresenter;
import com.havrylyuk.thenounproject.ui.main.MainMvpView;
import com.havrylyuk.thenounproject.ui.main.MainPresenter;
import com.havrylyuk.thenounproject.ui.search_dialog.FilterDialogMvpPresenter;
import com.havrylyuk.thenounproject.ui.search_dialog.FilterDialogMvpView;
import com.havrylyuk.thenounproject.ui.search_dialog.FilterDialogPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

@Module(includes = RxModule.class)
public class ActivityFragmentModule {

    private Activity activity;

    public ActivityFragmentModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity getActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context getActivityContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    MainMvpPresenter<MainMvpView> getMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    CollectionMvpPresenter<CollectionMvpView> getCollectionPresenter(
            CollectionPresenter<CollectionMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    CollectionDetailMvpPresenter<CollectionDetailMvpView> getCollectionDetailPresenter(
            CollectionDetailPresenter<CollectionDetailMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    SearchIconMvpPresenter<NounIconMvpView> getIconPresenter (
            SearchIconPresenter<NounIconMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    IconsMvpPresenter<IconsMvpView> getIconsPresenter (
            IconsPresenter<IconsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    CollectionsMvpPresenter<CollectionsMvpView> getCollectionsPresenter (
            CollectionsPresenter<CollectionsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    HomeMvpPresenter<HomeMvpView> getHomePresenter (
            HomePresenter<HomeMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    RecentMvpPresenter<NounIconMvpView> getRecentPresenter (
            RecentPresenter<NounIconMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    AboutMvpPresenter<AboutMvpView> getAboutPresenter (
            AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    FilterDialogMvpPresenter<FilterDialogMvpView> getSearchDialogPresenter(
            FilterDialogPresenter<FilterDialogMvpView> presenter) {
        return presenter;
    }

    // Adapters

    @Provides
    @ActivityScope
    CollectionAdapter getCollectionsAdapter(@ActivityContext Context context) {
        return new CollectionAdapter(context);
    }

    @Provides
    @ActivityScope
    IconsPagerAdapter getIconsPagerAdapter(@ActivityContext Context context) {
        return new IconsPagerAdapter(((AppCompatActivity)context).getSupportFragmentManager());
    }

    @Provides
    @ActivityScope
    NounIconAdapter getIconsAdapter(@ActivityContext Context context) {
        return new NounIconAdapter(context);
    }

    @Provides
    @ActivityScope
    RecentIconAdapter getRecentIconAdapter(@ActivityContext Context context) {
        return new RecentIconAdapter(context);
    }

    @Provides
    @ActivityScope
    RecentCardAdapter getRecentCardAdapter(@ActivityContext Context context) {
        return new RecentCardAdapter(context);
    }

    @Provides
    @ActivityScope
    SearchHistoryAdapter getSearchHistoryAdapter(@ActivityContext Context context) {
        return new SearchHistoryAdapter(context);
    }
}
