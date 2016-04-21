package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.adapters.PhotoGalleryAdapter;
import com.rudraksh.food.widgets.CirclePageIndicator;

/**
 * Created by Raju on 4/21/2016.
 */
public class PhotoGalleryFragment extends BaseFragment {

    private ViewPager viewPager;
    private CirclePageIndicator circlePageIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_gallery, container, false);
    }

    @Override
    protected void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.fragment_photo_gallery_vp);
        circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.fragment_photo_gallery_cpi);

        PhotoGalleryAdapter photoGalleryAdapter = new PhotoGalleryAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(photoGalleryAdapter);
        circlePageIndicator.setViewPager(this.viewPager);
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(getString(R.string.photo_gallery));
        SecondActivity.getInstance().hideBackButton();
        SecondActivity.getInstance().getShareImageView().setVisibility(View.GONE);
    }
}
