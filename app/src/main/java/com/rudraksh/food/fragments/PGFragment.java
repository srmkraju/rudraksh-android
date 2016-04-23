package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudraksh.food.R;

/**
 * Created by dell3 on 23/4/16.
 */
public class PGFragment extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pg, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initToolbar() {

    }
}
