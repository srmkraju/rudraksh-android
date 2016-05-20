package com.rudraksh.food.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;
import com.rudraksh.food.adapters.Fragment_offer_Adapter;
import com.rudraksh.food.models.OfferResponseModel;
import com.rudraksh.food.utils.Logger;
import com.rudraksh.food.webservices.RestClient;
import com.rudraksh.food.webservices.RetrofitCallback;

import java.util.ArrayList;

import retrofit.Call;

/**
 * Created by Raju on 4/21/2016.
 */
public class OffersFragment extends BaseFragment {
    private FrameLayout emptyFrameView;
    private Context context = getContext();
    private RecyclerView fragment_offer_recyclerview;
    private RecyclerView.Adapter fragment_offer_adapter;
    private ArrayList<OfferResponseModel.OfferData> offerData = new ArrayList<OfferResponseModel.OfferData>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }

    @Override
    protected void initView(View view) {
        emptyFrameView = (FrameLayout) view.findViewById(R.id.emptyView);
        fragment_offer_recyclerview = (RecyclerView)view.findViewById(R.id.fragment_offers_rv);
        fragment_offer_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        fragment_offer_adapter = new Fragment_offer_Adapter(context,offerData);
        fragment_offer_recyclerview.setAdapter(fragment_offer_adapter);
        getOfferData();
    }

    private void getOfferData() {
        final ProgressDialog dialog= Logger.showProgressDialog(getContext());
        final Call<OfferResponseModel> offerResponseModelCall = RestClient.getInstance().getApiInterface().getOfferData();
        offerResponseModelCall.enqueue(new RetrofitCallback<OfferResponseModel>(getContext(),dialog) {
            @Override
            public void onSuccess(OfferResponseModel offerResponseModel) {
                if(offerResponseModel.isresponse())
                {
                    offerResponseModel.getData().trimToSize();
                    offerData.addAll(offerResponseModel.getData());
                    fragment_offer_adapter.notifyDataSetChanged();
                }
                else {
                    emptyFrameView.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    @Override
    protected void initToolbar() {
        MainActivity.getInstance().setActionBarTitle(getString(R.string.offers));
        MainActivity.getInstance().hideBackButton();
        MainActivity.getInstance().getShareImageView().setVisibility(View.GONE);
    }
}
