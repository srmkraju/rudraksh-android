package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudraksh.food.R;

/**
 * Created by Raju on 4/21/2016.
 */
public class PhotoGalleryTwoFragment extends BaseFragment {


    public PhotoGalleryTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo_two, container, false);
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initToolbar() {

    }

}
