package com.havrylyuk.thenounproject.ui.collections;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.ui.base.BaseActivity;
import com.havrylyuk.thenounproject.ui.collections.collection.CollectionFragment;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class CollectionsActivity extends BaseActivity
        implements CollectionsMvpView {

    @Inject
    CollectionsMvpPresenter<CollectionsMvpView> presenter;

    @BindView
    (R.id.toolbar) Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);
        getActivityFragmentComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.attachView(this);
        init();
        presenter.setActivityTitle(getString(R.string.item_collections));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CollectionFragment())
                .commit();
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
