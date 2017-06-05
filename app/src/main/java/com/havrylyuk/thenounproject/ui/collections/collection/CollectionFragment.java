package com.havrylyuk.thenounproject.ui.collections.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.events.NounCollectionsEvent;
import com.havrylyuk.thenounproject.ui.base.BaseSearchFragment;
import com.havrylyuk.thenounproject.ui.collection_detail.CollectionDetailActivity;
import com.havrylyuk.thenounproject.utils.EndlessRecyclerViewScrollListener;
import com.havrylyuk.thenounproject.utils.RecyclerItemUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class CollectionFragment extends BaseSearchFragment
            implements CollectionMvpView, RecyclerItemUtils.OnItemClickListener,
                       SwipeRefreshLayout.OnRefreshListener {

    @Inject
    CollectionMvpPresenter<CollectionMvpView> presenter;
    @Inject
    CollectionAdapter collectionsAdapter;

    @BindView(R.id.recycler_collections)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout_collections)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.txt_error)
    TextView txtError;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        getActivityFragmentComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.attachView(this);
        init();
        return view;
    }

    @Override
    protected void init() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.drawerBackgroundColor);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // endless Scroll Listener
        EndlessRecyclerViewScrollListener scrollListener =
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (TextUtils.isEmpty(searchQuery)) {
                    loadDataPage(page);
                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.setAdapter(collectionsAdapter);
        RecyclerItemUtils.addTo(recyclerView).setOnItemClickListener(this);
        // load noun collections fist page
        loadDataPage(1);
    }

    private void loadDataPage(int page) {
        if (TextUtils.isEmpty(searchQuery)){
            presenter.loadCollections(page);
        } else {
            presenter.loadCollections(searchQuery);
        }
    }

    @Override
    protected void closeSearch() {
        collectionsAdapter.clear();
        loadDataPage(1);
    }

    @Override
    protected void startSearch(String quety, boolean isPublicOnly) {
        collectionsAdapter.clear();
        presenter.loadCollections(searchQuery);
    }

    @Override
    protected void setupSearchOptions(SearchView searchView) {

    }

    @Override
    public void showCollections(List<NounCollection> nounCollectionList) {
        showRecycler(true);
        collectionsAdapter.addData(nounCollectionList);
    }

    @Override
    public void showEmptyView() {
        showRecycler(false);
        txtError.setText(R.string.error_collections);
    }

    @Override
    public void onError(String message) {
        showRecycler(false);
        txtError.setText(message);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        startActivity(new Intent(getActivity(), CollectionDetailActivity.class));
        EventBus.getDefault().postSticky(
                new NounCollectionsEvent(collectionsAdapter.getItem(position)));
    }

    @Override
    public void onRefresh() {
        collectionsAdapter.clear();
        loadDataPage(1);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    private void showRecycler(boolean visibility) {
        recyclerView.setVisibility(visibility ? View.VISIBLE : View.GONE);
        txtError.setVisibility(visibility ? View.GONE : View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }
}
