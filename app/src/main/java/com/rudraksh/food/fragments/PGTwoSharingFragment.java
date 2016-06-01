package com.rudraksh.food.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;
import com.rudraksh.food.utils.Constant;

import java.util.Locale;

/**
 * Created by Raju on 4/27/2016.
 */
public class PGTwoSharingFragment extends BaseFragment implements View.OnClickListener{
    //private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton callFloatingButton;
    private ImageView sharingImageView;
    private LinearLayout pgParentLinearLayout;
    private String sharing;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pg_sharing, container, false);
    }
    @Override
    protected void initView(View view) {
        callFloatingButton = (FloatingActionButton) view.findViewById(R.id.fragment_pg_fab_call);
        sharingImageView = (ImageView) view.findViewById(R.id.fragment_pg_sharing_iv);
        pgParentLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_pg_linear);
        if(getArguments()!=null){
            sharing = getArguments().getString("sharing");
        }
        if(!TextUtils.isEmpty(sharing)){
            if(sharing.equalsIgnoreCase("OneSharing")){
                sharingImageView.setImageResource(R.drawable.single_sharing_pg);
                MainActivity.getInstance().setActionBarTitle(getString(R.string.one_sharing));
            } else if(sharing.equalsIgnoreCase("TwoSharing")){
                sharingImageView.setImageResource(R.drawable.two_share_pg);
                MainActivity.getInstance().setActionBarTitle(getString(R.string.two_sharing));
            } else if(sharing.equalsIgnoreCase("ThreeSharing")){
                sharingImageView.setImageResource(R.drawable.three_share_pg);
                MainActivity.getInstance().setActionBarTitle(getString(R.string.three_sharing));
            }
        }
        callFloatingButton.setOnClickListener(this);
    }

    @Override
    protected void initToolbar() {
        MainActivity.getInstance().showBackButton();
        MainActivity.getInstance().getShareImageView().setVisibility(View.VISIBLE);
        MainActivity.getInstance().getShareImageView().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.row_toolbar_iv_share:
                shareImage();
                break;
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

    private void shareImage() {
        pgParentLinearLayout.setDrawingCacheEnabled(true);
        pgParentLinearLayout.buildDrawingCache();
        Bitmap bm = Bitmap.createBitmap(pgParentLinearLayout.getDrawingCache());
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                bm, "Image Description", null);
        Uri uri = Uri.parse(path);
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}
