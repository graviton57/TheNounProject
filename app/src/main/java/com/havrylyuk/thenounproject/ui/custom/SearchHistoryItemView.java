package com.havrylyuk.thenounproject.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.havrylyuk.thenounproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Igor Havrylyuk on 01.06.2017.
 */
public class SearchHistoryItemView extends RelativeLayout {

    @BindView
    (R.id.txt_hint) TextView txtHint;
    @BindView
    (R.id.txt_value) TextView txtValue;
    @BindView
    (R.id.img_icon) SimpleDraweeView imgIcon;
    @BindView
    (R.id.view_bottom_line) View viewBottomLine;

    public SearchHistoryItemView(Context context) {
        super(context);
    }

    public SearchHistoryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SearchHistoryItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @SuppressWarnings("deprecation")
    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.view_property_layout, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchHistoryItemView);
        this.txtHint.setText(typedArray.getString(R.styleable.SearchHistoryItemView_hintDescription));
        this.txtValue.setText(typedArray.getString(R.styleable.SearchHistoryItemView_value));
        this.imgIcon.setImageResource(
                typedArray.getResourceId(R.styleable.SearchHistoryItemView_iconDescription, -1));
        this.viewBottomLine.setVisibility(
                typedArray.getBoolean(R.styleable.SearchHistoryItemView_bottomLineVisibility, true)
                        ? View.VISIBLE : View.GONE);

        typedArray.recycle();
    }

    public void setValue(String valueTxt) {
        txtValue.setText(valueTxt);
    }

    public void setImgIcon(String uri){
        imgIcon.setImageURI(Uri.parse(uri));
    }

    public void setTxtHint(String hint){
        txtHint.setText(hint);
    }
}
