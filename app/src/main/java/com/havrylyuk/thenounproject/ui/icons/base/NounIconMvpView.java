package com.havrylyuk.thenounproject.ui.icons.base;

import com.havrylyuk.thenounproject.data.remote.model.NounIcon;
import com.havrylyuk.thenounproject.ui.base.BaseMvpView;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public interface NounIconMvpView extends BaseMvpView {

    void showIcons(List<NounIcon> nounIcons);

    void showEmptyView();

}
