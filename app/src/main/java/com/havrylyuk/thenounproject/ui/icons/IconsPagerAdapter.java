package com.havrylyuk.thenounproject.ui.icons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.havrylyuk.thenounproject.ui.icons.shearch_icon.SearchIconFragment;
import com.havrylyuk.thenounproject.ui.icons.recent.RecentFragment;

/**
 * Created by Igor Havrylyuk on 31.05.2017.
 */

public class IconsPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public IconsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SearchIconFragment.newInstance();
            case 1:
                return RecentFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public void setCount(int count) {
        tabCount = count;
    }
}
