package com.havrylyuk.thenounproject.ui.icons.base;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.ui.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class NounIconAdapter extends BaseRecyclerViewAdapter<NounIconAdapter.ViewHolder, NounIcon> {

    private Context context;

    @Inject
    public NounIconAdapter(Context context) {
        this.context = context;
        setData(new ArrayList<NounIcon>());
    }

    @Override
    public NounIconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NounIconAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NounIcon nounIcon = getItem(position);
        //set values
        holder.txtCounter.setText(String.valueOf(position + 1));
        holder.txtAttrib.setText(nounIcon.getAttribution());
        holder.txtName.setText(nounIcon.getDateUploaded());
        holder.imageNoun.setImageURI(Uri.parse(nounIcon.getPreviewUrl()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_item_col_name) TextView txtAttrib;
        @BindView(R.id.txt_item_author_name) TextView txtName;
        @BindView(R.id.txt_counter) TextView txtCounter;
        @BindView(R.id.icon_noun) ImageView imageNoun;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
