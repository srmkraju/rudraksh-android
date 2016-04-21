package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;

/**
 * Created by Raju on 4/21/2016.
 */
public class OffersFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(getString(R.string.offers));
        SecondActivity.getInstance().hideBackButton();
        SecondActivity.getInstance().getShareImageView().setVisibility(View.GONE);
    }
}
