package com.havrylyuk.thenounproject.ui.about;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.havrylyuk.thenounproject.R;
import com.havrylyuk.thenounproject.injection.component.ActivityFragmentComponent;
import com.havrylyuk.thenounproject.ui.base.BaseDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public class AboutDialog extends BaseDialog  implements AboutMvpView {


    @Inject
    AboutMvpPresenter<AboutMvpView> presenter;

    private static final String ABOUT_DIALOG_TAG = "ABOUT_DIALOG_TAG";

    @BindView(R.id.button_ok)
    Button closeButton;

    @BindView(R.id.dialog_message)
    TextView message;

    public static AboutDialog newInstance() {
        Bundle args = new Bundle();
        AboutDialog fragment = new AboutDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_about, container, false);
        ActivityFragmentComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.attachView(this);
        }
        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, ABOUT_DIALOG_TAG);
    }

    @Override
    protected void init(View view) {
        message.setText(fromHtml(getString(R.string.about_content)));
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCloseClick();
            }
        });
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(ABOUT_DIALOG_TAG);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    @SuppressWarnings({"deprecation"})
    private CharSequence fromHtml(@NonNull String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(text);
        }
    }

}
