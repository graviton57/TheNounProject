package com.havrylyuk.thenounproject.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.havrylyuk.thenounproject.BuildConfig;
import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.ui.about.AboutDialog;
import com.havrylyuk.thenounproject.ui.base.BaseActivity;
import com.havrylyuk.thenounproject.ui.collections.CollectionsActivity;
import com.havrylyuk.thenounproject.ui.home.HomeFragment;
import com.havrylyuk.thenounproject.ui.icons.IconsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class MainActivity extends BaseActivity
    implements MainMvpView, NavigationView.OnNavigationItemSelectedListener  {

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityFragmentComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.attachView(this);
        init();
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name) {
                    @Override public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        hideKeyboard();
                    }

                    @Override public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }
                };

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_drawer_view);
        navigationView.setNavigationItemSelectedListener(this);
        // interact with presenter
        presenter.setUserInfo();
        presenter.setCurrentTitle(getString(R.string.app_name));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commit();
    }

    @Override
    public void setUserName(String userName) {
    }

    @Override
    public void setActivityTitle(String title) {
        setTitle(title);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.d("requestCode=%d, resultCode=%d,  data=%s", requestCode, requestCode, data.toString());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_login:
                Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
                //startActivityForResult(new Intent(this, LoginActivity.class), 0);
                break;
            case R.id.nav_collections:
                startActivity(new Intent(this, CollectionsActivity.class));
                break;
            case R.id.nav_icons:
                startActivity(new Intent(this, IconsActivity.class));
                break;
            case R.id.nav_legal:
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(BuildConfig.NOUN_PROJECT_BASE_URL + "/legal"));
                startActivity(intent);
                break;
            case R.id.nav_about:
                AboutDialog.newInstance().show(getSupportFragmentManager());
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
