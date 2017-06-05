package com.havrylyuk.thenounproject.ui.main;



import com.havrylyuk.thenounproject.ui.base.BaseMvpView;


/**
 * Created by Igor Havrylyuk on 21.05.2017.
 */

public interface MainMvpView extends BaseMvpView {

    void setUserName(String userName);

    void setActivityTitle(String title);

}
