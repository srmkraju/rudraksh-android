package com.rudraksh.food.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rudraksh.food.fragments.FoodTypeFragment;
import com.rudraksh.food.fragments.TabFragment2;
import com.rudraksh.food.fragments.ServiceFragment;

/**
 * Created by Raju on 4/16/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private int tabPosition;
    public PagerAdapter(FragmentManager fm, int NumOfTabs, int tabPosition) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabPosition = tabPosition;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FoodTypeFragment tab1 = new FoodTypeFragment();
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                return tab2;
            case 2:
                ServiceFragment tab3 = new ServiceFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
