package com.havrylyuk.thenounproject.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.local.db.model.OrmHistory;
import com.havrylyuk.thenounproject.data.local.db.model.SearchType;
import com.havrylyuk.thenounproject.ui.base.BaseRecyclerViewAdapter;
import com.havrylyuk.thenounproject.ui.custom.SearchHistoryItemView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.havrylyuk.thenounproject.data.local.db.AppDbHelper.HISTORY_DATE_FORMAT;

/**
 * Created by Igor Havrylyuk on 02.06.2017.
 */

public class SearchHistoryAdapter
        extends BaseRecyclerViewAdapter<SearchHistoryAdapter.CardViewHolder, OrmHistory> {

    public static final int VIEW_TYPE_ICON = 1;
    public static final int VIEW_TYPE_COLL = 2;

    private Context context;

    @Inject
    public SearchHistoryAdapter(Context context) {
        this.context = context;
        setData(new ArrayList<OrmHistory>());
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new CardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_history, parent, false));
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        OrmHistory ormHistory = getItem(position);
        holder.itemView.setTxtHint(ormHistory.getQuery());
        if (getItemViewType(position)==VIEW_TYPE_ICON){
            holder.itemView.setValue(
                    context.getString(R.string.format_search_history_icon, ormHistory.getDate()));
            holder.itemView.setImgIcon(ormHistory.getPreviewUrl());
        } else if (getItemViewType(position)==VIEW_TYPE_COLL){
            holder.itemView.setValue(
                    context.getString(R.string.format_search_history_coll, ormHistory.getDate()));
        } else {
            holder.itemView.setValue(ormHistory.getDate().toString(HISTORY_DATE_FORMAT));
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position)!=null){
           SearchType type = getItem(position).getType();
            if (type==SearchType.ICONS){
                return VIEW_TYPE_ICON;
            }
            if (type==SearchType.COLLECTIONS){
                return VIEW_TYPE_COLL;
            }
        }
        return super.getItemViewType(position);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_search_history) SearchHistoryItemView itemView;

        public CardViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
