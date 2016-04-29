package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.Logger;

/**
 * Created by dell3 on 23/4/16.
 */
public class PGFragment extends BaseFragment implements View.OnClickListener{

    private RelativeLayout PgOneSharingRelativeLayout;
    private RelativeLayout PgTwoShareingRelativeLayout;
    private RelativeLayout PgThreeSharingRelativeLayout;
    private CoordinatorLayout pgCoordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pg, container, false);
    }

    @Override
    protected void initView(View view) {
        PgOneSharingRelativeLayout = (RelativeLayout) view.findViewById(R.id.fragment_pg_rl_onesharing);
        pgCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.fargment_pg_coordinatorLayout);
        PgTwoShareingRelativeLayout = (RelativeLayout) view.findViewById(R.id.fragment_pg_type_cv_two_share);
        PgThreeSharingRelativeLayout = (RelativeLayout) view.findViewById(R.id.fragment_pg_type_cv_three_share);
        PgOneSharingRelativeLayout.setOnClickListener(this);
        PgTwoShareingRelativeLayout.setOnClickListener(this);
        PgThreeSharingRelativeLayout.setOnClickListener(this);
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().showBackButton();
        SecondActivity.getInstance().getShareImageView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        final Bundle bundle = new Bundle();
        switch(v.getId()){
            case R.id.fragment_pg_rl_onesharing:
                Logger.snackBar(pgCoordinatorLayout,getActivity(),getString(R.string.no_one_sharing));
                break;
            case R.id.fragment_pg_type_cv_two_share:
                bundle.putString(Constant.CARD_NAME,getString(R.string.two_sharing));
                final Fragment twoSharingFragment = new PGTwoSharingFragment();
                twoSharingFragment.setArguments(bundle);
                addFragment(this, twoSharingFragment, true);
                break;
            case R.id.fragment_pg_type_cv_three_share:
                Logger.snackBar(pgCoordinatorLayout,getActivity(),getString(R.string.no_three_sharing));
                break;
        }
    }
}
