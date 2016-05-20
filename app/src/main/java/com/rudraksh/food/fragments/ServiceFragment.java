package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;

/**
 * Created by Raju on 4/16/2016.
 */
public class ServiceFragment extends BaseFragment implements View.OnClickListener{

    private CardView serviceFragmentCVFood;
    private CardView serviceFragmentCVPG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_service, container, false);
    }

    @Override
    protected void initView(View view) {
        serviceFragmentCVFood = (CardView) view.findViewById(R.id.fragment_service_cv_food);
        serviceFragmentCVPG = (CardView) view.findViewById(R.id.fragment_service_cv_pg);

        serviceFragmentCVFood.setOnClickListener(this);
        serviceFragmentCVPG.setOnClickListener(this);
    }

    @Override
    protected void initToolbar() {
        MainActivity.getInstance().setActionBarTitle(getString(R.string.services));
        MainActivity.getInstance().hideBackButton();
        MainActivity.getInstance().getShareImageView().setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        final Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.fragment_service_cv_food:
                final Fragment foodTypeFragment = new FoodTypeFragment();
                //bundle.putString("foodService","foodService");
                //foodTypeFragment.setArguments(bundle);
                addFragment(this, foodTypeFragment, true);
                break;
            case R.id.fragment_service_cv_pg:
                final PGFragment pgFragment = new PGFragment();
                addFragment(this, pgFragment, true);
                break;
        }
    }

}
