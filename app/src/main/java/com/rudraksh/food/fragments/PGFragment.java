package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;
import com.rudraksh.food.utils.Constant;

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
        MainActivity.getInstance().setActionBarTitle(getString(R.string.paying_guest));
        MainActivity.getInstance().showBackButton();
        MainActivity.getInstance().getShareImageView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        final Bundle bundle = new Bundle();
        switch(v.getId()){
            case R.id.fragment_pg_rl_onesharing:
                bundle.putString(Constant.CARD_NAME,getString(R.string.two_sharing));
                final Fragment oneSharingFragment = new PGTwoSharingFragment();
                bundle.putString("sharing","OneSharing");
                oneSharingFragment.setArguments(bundle);
                addFragment(this, oneSharingFragment, true);
                break;
            case R.id.fragment_pg_type_cv_two_share:
                bundle.putString(Constant.CARD_NAME,getString(R.string.two_sharing));
                final Fragment twoSharingFragment = new PGTwoSharingFragment();
                bundle.putString("sharing","TwoSharing");
                twoSharingFragment.setArguments(bundle);
                addFragment(this, twoSharingFragment, true);
                break;
            case R.id.fragment_pg_type_cv_three_share:
                bundle.putString(Constant.CARD_NAME,getString(R.string.two_sharing));
                final Fragment threeSharingFragment = new PGTwoSharingFragment();
                bundle.putString("sharing","ThreeSharing");
                threeSharingFragment.setArguments(bundle);
                addFragment(this, threeSharingFragment, true);
                break;
        }
    }
}
