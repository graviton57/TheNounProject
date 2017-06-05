package com.havrylyuk.thenounproject.ui.search_dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.events.SearchParamEvent;
import com.havrylyuk.thenounproject.injection.component.ActivityFragmentComponent;
import com.havrylyuk.thenounproject.ui.base.BaseDialog;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Igor Havrylyuk on 01.06.2017.
 */

public class FilterDialog extends BaseDialog implements FilterDialogMvpView {


    private static final String SEARCH_DIALOG_TAG = "SEARCH_DIALOG_TAG";

    @Inject
    FilterDialogMvpPresenter<FilterDialogMvpView> presenter;

    @BindView(R.id.public_check_box)
    CheckBox checkBox;

    @BindView(R.id.btn_submit)
    Button submitButton;

    public static FilterDialog newInstance() {
        Bundle args = new Bundle();
        FilterDialog fragment = new FilterDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_filter, container, false);
        ActivityFragmentComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.attachView(this);
        }
        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, SEARCH_DIALOG_TAG);
    }

    @Override
    protected void init(View view) {
        presenter.loadFilterValue();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSubmitClick(checkBox.isChecked());
            }
        });
    }

    @Override
    public void onFilterValueChange(boolean neValue) {
        EventBus.getDefault().postSticky(new SearchParamEvent(neValue));
    }

    @Override
    public void showFilterValue(boolean value) {
        checkBox.setChecked(value);
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(SEARCH_DIALOG_TAG);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }
}
