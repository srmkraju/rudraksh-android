package com.rudraksh.food.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.Logger;
import com.rudraksh.food.utils.SendMail;

import java.util.Locale;

/**
 * Created by Raju on 4/17/2016.
 */
public class ContactFragment extends BaseFragment implements View.OnClickListener{

    private Button contactFrgmentBtnMukesh;
    private Button contactFragmentBtnNaresh;
    private EditText contactFragmentFeedback;
    private Button contactFragmentSubmit;
    private SendMail sm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    @Override
    protected void initView(View view) {
        contactFrgmentBtnMukesh = (Button) view.findViewById(R.id.frament_contact_btn_mukesh);
        contactFragmentBtnNaresh = (Button) view.findViewById(R.id.frament_contact_btn_naresh);
        contactFragmentFeedback = (EditText) view.findViewById(R.id.fragment_contact_edt_feedback);
        contactFragmentSubmit = (Button) view.findViewById(R.id.fragment_contact_btn_submit);
        contactFrgmentBtnMukesh.setOnClickListener(this);
        contactFragmentBtnNaresh.setOnClickListener(this);
        contactFragmentSubmit.setOnClickListener(this);
    }

    @Override
    protected void initToolbar() {
        MainActivity.getInstance().setActionBarTitle(getString(R.string.contact_us));
        MainActivity.getInstance().hideBackButton();
        MainActivity.getInstance().getShareImageView().setVisibility(View.GONE);
        if(getActivity() instanceof MainActivity){
            ActionBar actionBar = MainActivity.getInstance().getSupportActionBar();
            actionBar.show();
        } else {
            ActionBar actionBar = MainActivity.getInstance().getSupportActionBar();
            actionBar.show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.frament_contact_btn_mukesh:
                callToMukesh();
                break;
            case R.id.frament_contact_btn_naresh:
                callToNaresh();
                break;
            case R.id.fragment_contact_btn_submit:
                sendFeedBack();
                break;
        }
    }

    private void sendFeedBack(){
        if(!TextUtils.isEmpty(contactFragmentFeedback.getText().toString())){
            sm = new SendMail(getActivity(),"mukesh.prajapati22@gmail.com","Reg: Feedback", contactFragmentFeedback.getText().toString());
            sm.execute();
            contactFragmentFeedback.setText("");
            Logger.toast(getActivity(),getString(R.string.thank_you_feedback));
        } else{
            Logger.toast(getActivity(),getString(R.string.enter_feedback));
        }

    }

    private void callToMukesh(){
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
