package com.havrylyuk.thenounproject.ui.home;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.ui.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Igor Havrylyuk on 02.06.2017.
 */

public class RecentCardAdapter
        extends BaseRecyclerViewAdapter<RecentCardAdapter.CardViewHolder, NounIcon> {


    private Context context;

    @Inject
    public RecentCardAdapter(Context context) {
        this.context = context;
        setData(new ArrayList<NounIcon>());
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new CardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_icon, parent, false));
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        NounIcon nounIcon = getItem(position);
        holder.imageView.setImageURI(Uri.parse(nounIcon.getPreviewUrl()));
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_card_recent) ImageView imageView;

        public CardViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
