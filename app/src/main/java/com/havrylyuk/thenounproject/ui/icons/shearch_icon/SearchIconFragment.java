package com.havrylyuk.thenounproject.ui.icons.shearch_icon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.havrylyuk.thenounproject.BuildConfig;
import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.events.SearchParamEvent;
import com.havrylyuk.thenounproject.ui.base.BaseSearchFragment;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconAdapter;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconMvpView;
import com.havrylyuk.thenounproject.ui.search_dialog.FilterDialog;
import com.havrylyuk.thenounproject.utils.EndlessRecyclerViewScrollListener;
import com.havrylyuk.thenounproject.utils.RecyclerItemUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class SearchIconFragment extends BaseSearchFragment
            implements NounIconMvpView, RecyclerItemUtils.OnItemClickListener,
                       SwipeRefreshLayout.OnRefreshListener {

    @Inject
    SearchIconMvpPresenter<NounIconMvpView> presenter;
    @Inject
    NounIconAdapter iconAdapter;

    @BindView(R.id.recycler_collections)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout_collections)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.txt_error)
    TextView txtError;

    public static SearchIconFragment newInstance() {
        Bundle args = new Bundle();
        SearchIconFragment fragment = new SearchIconFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        // endless
        EndlessRecyclerViewScrollListener scrollListener =
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPageData(page);
            }
        };
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.setAdapter(iconAdapter);
        RecyclerItemUtils.addTo(recyclerView).setOnItemClickListener(this);
        // load noun icons fist page
        //loadPageData(1);
    }

    private void loadPageData(int page) {
        if (!TextUtils.isEmpty(searchQuery)){
            presenter.loadRequestedIcons(searchQuery, page, false);
        }
    }

    @Override
    protected void closeSearch() {
        iconAdapter.clear();
        loadPageData(1);
    }

    @Override
    protected void startSearch(String query, boolean isPublicOnly) {
        iconAdapter.clear();
        presenter.loadRequestedIcons(searchQuery, 1, false);
    }

    @Override
    protected void setupSearchOptions(SearchView searchView) {
        final ImageButton searchOptionsButton =
                (ImageButton) getActivity().getLayoutInflater().inflate(R.layout.search_view_options_button, null);
        LinearLayout searchViewSearchPlate = (LinearLayout) searchView.findViewById(R.id.search_plate);
        searchViewSearchPlate.addView(searchOptionsButton);
        searchOptionsButton.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        searchOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchOptionsButtonClicked();
            }
        });
    }

    protected void onSearchOptionsButtonClicked(){
        FilterDialog.newInstance().show(getActivity().getSupportFragmentManager());
    }

    @Override
    public void showIcons(List<NounIcon> nounIconList) {
        showRecycler(true);
        iconAdapter.addData(nounIconList);
    }

    @Override
    public void showEmptyView() {
        showRecycler(false);
        txtError.setText(R.string.error_icons);
    }

    @Override
    public void onError(String message) {
        super.onError(message);
        showRecycler(false);
        txtError.setText(message);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        String finalUrl = BuildConfig.NOUN_PROJECT_BASE_URL
                + iconAdapter.getItem(position).getPermalink();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl));
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        if (!TextUtils.isEmpty(searchQuery)){
            loadPageData(1);
        }
        showRecycler(true);
    }

    private void showRecycler(boolean visibility) {
        recyclerView.setVisibility(visibility ? View.VISIBLE : View.GONE);
        txtError.setVisibility(visibility ? View.GONE : View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe(sticky=true, threadMode = ThreadMode.MAIN)
    public void onEvent(SearchParamEvent paramEvent) {
        iconAdapter.clear();
        if (!TextUtils.isEmpty(searchQuery)){
            presenter.loadRequestedIcons(searchQuery, 1, paramEvent.isPublic());
        }
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
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

}
