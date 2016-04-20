package com.rudraksh.food.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;

import java.util.Locale;

/**
 * Created by Raju on 4/17/2016.
 */
public class ContactFragment extends BaseFragment implements View.OnClickListener{

    private CardView contactFrgmentCVMukesh;
    private CardView contactFragmentCVNaresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    @Override
    protected void initView(View view) {
        contactFrgmentCVMukesh = (CardView) view.findViewById(R.id.frament_contact_cv_mukesh);
        contactFragmentCVNaresh = (CardView) view.findViewById(R.id.frament_contact_cv_naresh);
        contactFrgmentCVMukesh.setOnClickListener(this);
        contactFragmentCVNaresh.setOnClickListener(this);
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(getString(R.string.contact_us));
        SecondActivity.getInstance().showBackButton();
        SecondActivity.getInstance().getShareImageView().setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.frament_contact_cv_mukesh:
                callToMukesh();
                break;
            case R.id.frament_contact_cv_naresh:
                callToNaresh();
                break;
        }
    }

    private void callToMukesh(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(getString(R.string.mukesh_91_9409409408)));
        startActivity(callIntent);
        /*Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(String.format(Locale.getDefault(), "tel:%s", getString(R.string.mukesh_91_9409409408))));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        Constant.REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }
        startActivity(callIntent);*/
    }

    private void callToNaresh(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(String.format(Locale.getDefault(), "tel:%s", getString(R.string.naresh_91_8866660906))));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        Constant.REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }
        startActivity(callIntent);
    }
}
