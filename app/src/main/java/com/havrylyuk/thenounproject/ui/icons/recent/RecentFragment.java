package com.havrylyuk.thenounproject.ui.icons.recent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.havrylyuk.thenounproject.BuildConfig;
import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.ui.base.BaseFragment;
import com.havrylyuk.thenounproject.ui.icons.base.NounIconMvpView;
import com.havrylyuk.thenounproject.utils.EndlessRecyclerViewScrollListener;
import com.havrylyuk.thenounproject.utils.RecyclerItemUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public class RecentFragment extends BaseFragment
        implements NounIconMvpView, RecyclerItemUtils.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private EndlessRecyclerViewScrollListener scrollListener;

    @Inject
    RecentMvpPresenter<NounIconMvpView> presenter;
    @Inject
    RecentIconAdapter iconAdapter;

    @BindView(R.id.recycler_collections)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout_collections)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.txt_error)
    TextView txtError;

    public static RecentFragment newInstance() {
        Bundle args = new Bundle();
        RecentFragment fragment = new RecentFragment();
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
        swipeRefreshLayout.setColorSchemeResources(R.color.colorDrawerBackground);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // endless
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
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
        loadPageData(1);
    }

    private void loadPageData(int page) {
        Timber.i("loadPageData page = %d", page);
        presenter.loadRecentUploadIcons(page);
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
        // ignore, we want different type of error for this screen
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
        iconAdapter.clear();
        loadPageData(1);
    }

    private void showRecycler(boolean visibility) {
        recyclerView.setVisibility(visibility ? View.VISIBLE : View.GONE);
        txtError.setVisibility(visibility ? View.GONE : View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

}
