package com.havrylyuk.thenounproject.ui.collection_detail;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.havrylyuk.thenounproject.BuildConfig;
import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.events.NounCollectionsEvent;
import com.havrylyuk.thenounproject.ui.base.BaseActivity;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconAdapter;
import com.havrylyuk.thenounproject.utils.RecyclerItemUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */
public class CollectionDetailActivity extends BaseActivity
        implements CollectionDetailMvpView, RecyclerItemUtils.OnItemClickListener {

    @Inject
    CollectionDetailMvpPresenter<CollectionDetailMvpView> presenter;
    @Inject
    NounIconAdapter iconAdapter;

    @BindView(R.id.header_author_name)
    TextView txtAuthorName;
    @BindView(R.id.header_author_location)
    TextView txtAuthorLoc;
    @BindView(R.id.header_date_created)
    TextView txtDateCreate;
    @BindView(R.id.header_description)
    TextView txtDesc;
    @BindView(R.id.header_icons_count)
    TextView txtIconsCount;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_icons)
    RecyclerView recyclerView;
    @BindView(R.id.txt_error)
    TextView txtError;

    private NounCollection collection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_col_detail);
        getActivityFragmentComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.attachView(this);
    }

    @Override
    protected void init() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout appBarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(collection.getName());
        } else if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(collection.getName());
        }
        // setting values
        txtAuthorName.setText(collection.getAuthor().getName());
        txtAuthorLoc.setText(collection.getAuthor().getLocation());
        txtDateCreate.setText(collection.getDate_created());
        txtDesc.setText(collection.getDescription());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(iconAdapter);
        RecyclerItemUtils.addTo(recyclerView).setOnItemClickListener(this);
        // load selected noun collections icons
        presenter.getCollectionIconsById(collection.getId());
    }

    @Subscribe(sticky=true, threadMode = ThreadMode.MAIN)
    public void onEvent(NounCollectionsEvent collectionsEvent) {
        collection = collectionsEvent.getNounCollection();
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showIcons(List<NounIcon> nounIcons) {
        showRecycler(true);
        iconAdapter.addData(nounIcons);
        txtIconsCount.setText(getString(R.string.format_icons_count, nounIcons.size()));
    }

    @Override
    public void onError(String message) {
        super.onError(message);
        txtError.setText(message);
    }

    @Override
    public void showEmptyView() {
        showRecycler(false);
        txtIconsCount.setText(R.string.without_icons);
    }

    private void showRecycler(boolean visibility) {
        recyclerView.setVisibility(visibility ? View.VISIBLE : View.GONE);
        txtError.setVisibility(visibility ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        String finalUrl = BuildConfig.NOUN_PROJECT_BASE_URL
                + iconAdapter.getItem(position).getPermalink();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl));
        startActivity(intent);
    }
}
