package com.havrylyuk.thenounproject.ui.home;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.local.db.model.OrmHistory;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.ui.base.BaseFragment;
import com.havrylyuk.thenounproject.ui.collections.CollectionsActivity;
import com.havrylyuk.thenounproject.ui.custom.SearchHistoryItemView;
import com.havrylyuk.thenounproject.ui.icons.IconsActivity;
import com.havrylyuk.thenounproject.utils.RecyclerItemUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public class HomeFragment extends BaseFragment
        implements HomeMvpView , Animator.AnimatorListener,
        SwipeRefreshLayout.OnRefreshListener,  RecyclerItemUtils.OnItemClickListener {

    @Inject
    HomeMvpPresenter<HomeMvpView> presenter;
    @Inject
    RecentCardAdapter recentCardAdapter;
    @Inject
    SearchHistoryAdapter historyCardAdapter;

    @BindView(R.id.home_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_card_recent)
    RecyclerView recyclerRecent;
    @BindView(R.id.recycler_card_history)RecyclerView recyclerHistory;
    @BindView(R.id.txt_recent_card_error)
    TextView txtRecentError;
    @BindView(R.id.txt_history_card_error)
    TextView txtHistoryError;
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.image_welcome)
    ImageView imageLogo;
    @BindView(R.id.text_logo)
    TextView textLogo;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivityFragmentComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        presenter.attachView(this);
        init();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        animationView.cancelAnimation();
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    public void startAnimation() {
        animationView.setProgress(0f);
        animationView.playAnimation();
        animationView.addAnimatorListener(this);
    }

    @Override
    public void showEmptyRecentUpload() {
        showRecentCardRecycler(false);
        txtRecentError.setText(R.string.error_icons);
    }

    private void showRecentCardRecycler(boolean visibility) {
        recyclerRecent.setVisibility(visibility ? View.VISIBLE : View.GONE);
        txtRecentError.setVisibility(visibility ? View.GONE : View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void showHistoryCardRecycler(boolean visibility) {
        recyclerHistory.setVisibility(visibility ? View.VISIBLE : View.GONE);
        txtHistoryError.setVisibility(visibility ? View.GONE : View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEmptySearchHistory() {
        showHistoryCardRecycler(false);
        txtHistoryError.setText(R.string.error_empty_history);
    }

    @Override
    public void showRecentUploads(List<NounIcon> list) {
        showRecentCardRecycler(true);
        recentCardAdapter.addData(list);
    }

    @Override
    public void showSearchHistory(List<OrmHistory> list) {
        showHistoryCardRecycler(true);
        historyCardAdapter.addData(list);
    }

    @Override
    protected void init() {
        startAnimation();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.drawerBackgroundColor);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        LinearLayoutManager horizontalLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerRecent.setLayoutManager(horizontalLayoutManager);
        recyclerRecent.setItemAnimator(new DefaultItemAnimator());
        recyclerRecent.setAdapter(recentCardAdapter);
        RecyclerItemUtils.addTo(recyclerRecent).setOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerHistory.setLayoutManager(linearLayoutManager);
        recyclerHistory.setItemAnimator(new DefaultItemAnimator());
        recyclerHistory.setAdapter(historyCardAdapter);
        RecyclerItemUtils.addTo(recyclerHistory).setOnItemClickListener(this);
        presenter.loadRecentUploadIcons();
        presenter.loadSearchHistory();
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        ObjectAnimator.ofFloat(animationView, View.ALPHA, 1f, 0f).start();
        ObjectAnimator.ofFloat(imageLogo, View.ALPHA, 0f, 1f).start();
        ObjectAnimator.ofFloat(textLogo, View.ALPHA, 0f, 1f).setDuration(2000).start();
    }

    public void onAnimationStart(Animator animation) {}

    public void onAnimationCancel(Animator animation) {}

    public void onAnimationRepeat(Animator animation) {}

    @Override
    public void onRefresh() {
        recentCardAdapter.clear();
        historyCardAdapter.clear();
        presenter.loadRecentUploadIcons();
        presenter.loadSearchHistory();
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        if (v instanceof SearchHistoryItemView){
            if (historyCardAdapter.getItemViewType(position)
                    == SearchHistoryAdapter.VIEW_TYPE_COLL) {
                startActivity(new Intent(getActivity(), CollectionsActivity.class));
            } else if (historyCardAdapter.getItemViewType(position)
                    == SearchHistoryAdapter.VIEW_TYPE_ICON) {
                startActivity(new Intent(getActivity(), IconsActivity.class));
            }
        } else {
            startActivity(new Intent(getActivity(), IconsActivity.class));
        }
    }
}
