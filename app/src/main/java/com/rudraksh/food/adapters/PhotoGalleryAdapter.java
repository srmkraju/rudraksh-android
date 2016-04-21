package com.rudraksh.food.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rudraksh.food.fragments.PhotoGalleryOneFragment;
import com.rudraksh.food.fragments.PhotoGalleryTwoFragment;

/**
 * Created by Raju on 4/21/2016.
 */
public class PhotoGalleryAdapter extends FragmentStatePagerAdapter {

    public PhotoGalleryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PhotoGalleryOneFragment();
                break;
            case 1:
                fragment = new PhotoGalleryTwoFragment();
                break;
            case 2:
                fragment = new PhotoGalleryOneFragment();
                break;
            case 3:
                fragment = new PhotoGalleryTwoFragment();
                break;
            case 4:
                fragment = new PhotoGalleryOneFragment();
                break;
        }
        return fragment;
    }
}
