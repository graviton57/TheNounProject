package com.havrylyuk.thenounproject.ui.collections.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.remote.model.NounCollection;
import com.havrylyuk.thenounproject.ui.base.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class CollectionAdapter
        extends BaseRecyclerViewAdapter<CollectionAdapter.ViewHolder, NounCollection> {

    private Context context;

    @Inject
    public CollectionAdapter(Context context) {
        this.context = context;
        setData(new ArrayList<NounCollection>());
    }

    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectionAdapter.ViewHolder(
                LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.item_collection, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NounCollection nounCollection = getItem(position);
        //set values
        holder.txtColName.setText(nounCollection.getName());
        holder.txtAuthorName.setText(nounCollection.getAuthor().getName());
        holder.txtCount.setText(String.valueOf(position + 1));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_item_col_name) TextView txtColName;
        @BindView(R.id.txt_item_author_name) TextView txtAuthorName;
        @BindView(R.id.txt_counter) TextView txtCount;
        @BindView(R.id.collection_icon) ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
