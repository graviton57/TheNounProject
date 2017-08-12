package com.havrylyuk.thenounproject.injection.component;

import com.havrylyuk.thenounproject.injection.module.ActivityFragmentModule;
import com.havrylyuk.thenounproject.injection.scope.ActivityScope;
import com.havrylyuk.thenounproject.ui.about.AboutDialog;
import com.havrylyuk.thenounproject.ui.collection_detail.CollectionDetailActivity;
import com.havrylyuk.thenounproject.ui.collections.CollectionsActivity;
import com.havrylyuk.thenounproject.ui.collections.collection.CollectionFragment;
import com.havrylyuk.thenounproject.ui.home.HomeFragment;
import com.havrylyuk.thenounproject.ui.icons.IconsActivity;
import com.havrylyuk.thenounproject.ui.icons.shearch_icon.SearchIconFragment;
import com.havrylyuk.thenounproject.ui.icons.recent.RecentFragment;
import com.havrylyuk.thenounproject.ui.login.LoginActivity;
import com.havrylyuk.thenounproject.ui.main.MainActivity;
import com.havrylyuk.thenounproject.ui.search_dialog.FilterDialog;


import dagger.Component;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityFragmentModule.class)
public interface ActivityFragmentComponent {

    void inject(MainActivity mainActivity);

    void inject(CollectionDetailActivity detailActivity);

    void inject(SearchIconFragment iconsFragment);

    void inject(CollectionFragment collectionsFragment);

    void inject(HomeFragment homeFragment);

    void inject(RecentFragment recentFragment);

    void inject(CollectionsActivity collectionsActivity);

    void inject(IconsActivity iconsActivity);

    void inject(FilterDialog dialog);

    void inject(AboutDialog dialog);

    void inject(LoginActivity loginActivity);
}
