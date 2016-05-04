package com.rudraksh.food.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;

import java.util.Locale;

/**
 * Created by Raju on 4/27/2016.
 */
public class PGTwoSharingFragment extends BaseFragment implements View.OnClickListener{
    //private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton callFloatingButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pg_sharing, container, false);
    }
    @Override
    protected void initView(View view) {
        callFloatingButton = (FloatingActionButton) view.findViewById(R.id.fragment_pg_fab_call);
        callFloatingButton.setOnClickListener(this);
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(getString(R.string.two_sharing));
        SecondActivity.getInstance().showBackButton();
        SecondActivity.getInstance().getShareImageView().setVisibility(View.GONE);
        //SecondActivity.getInstance().getShareImageView().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_pg_fab_call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(String.format(Locale.getDefault(), "tel:%s", getString(R.string.mukesh_91_9409409408))));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                Constant.REQUEST_CODE_ASK_PERMISSIONS);
                    }
                    return;
                }
                startActivity(callIntent);

                break;
        }
    }
}
