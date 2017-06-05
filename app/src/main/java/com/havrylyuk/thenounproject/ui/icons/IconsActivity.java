package com.havrylyuk.thenounproject.ui.icons;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IconsActivity extends BaseActivity
            implements IconsMvpView {

    private static final int PAGE_COUNT = 2;

    @Inject
    IconsMvpPresenter<IconsMvpView> presenter;

    @Inject
    IconsPagerAdapter mPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.icons_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icons);
        getActivityFragmentComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.attachView(this);
        presenter.setActivityTitle(getString(R.string.item_icons));
        init();
    }

    @Override
    protected void init() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mPagerAdapter.setCount(PAGE_COUNT);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.action_search)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.item_uploads)));
        //mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.item_favorite)));
        mViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void setActivityTitle(String title) {
        setTitle(title);
    }
}
