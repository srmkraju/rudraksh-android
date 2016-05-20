package com.rudraksh.food.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;
import com.rudraksh.food.adapters.FoodTypeAdapter;
import com.rudraksh.food.models.ProductListModel;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.OnRecyclerViewItemClickListener;
import com.rudraksh.food.webservices.RestClient;
import com.rudraksh.food.webservices.RetrofitCallback;
import com.rudraksh.food.widgets.AppImageView;

import java.util.ArrayList;

import retrofit.Call;

public class FoodTypeFragment extends BaseFragment implements OnRecyclerViewItemClickListener{


    private long mLastClickTime = 0;
    private RecyclerView foodTypeRecyclerView;
    private FoodTypeAdapter foodTypeRecyclerAdapter;

    private ArrayList<ProductListModel.ProductResponseData> productListModelArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_type, container, false);
    }

    @Override
    protected void initView(View view) {

        foodTypeRecyclerView = (RecyclerView)view.findViewById(R.id.food_fragment_rv);
        foodTypeRecyclerAdapter = new FoodTypeAdapter(getContext(),productListModelArrayList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        foodTypeRecyclerView.setLayoutManager(mLayoutManager);
        foodTypeRecyclerView.setAdapter(foodTypeRecyclerAdapter);
        getProductData();
    }
    private void getProductData() {
        try {
            final ProgressDialog dialog = com.rudraksh.food.utils.Logger.showProgressDialog(getContext());
            final Call<ProductListModel> productModelCall = RestClient.getInstance().getApiInterface().getProductData();
            productModelCall.enqueue(new RetrofitCallback<ProductListModel>(getContext(),dialog) {
                @Override
                public void onSuccess(ProductListModel productModel) {
                    if(productModel.isresponse()){
                        productModel.getData().trimToSize();
                        productListModelArrayList.addAll(productModel.getData());
                        foodTypeRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void initToolbar() {
        MainActivity.getInstance().setActionBarTitle(getString(R.string.food));
        MainActivity.getInstance().hideBackButton();
    }


    @Override
    public void onItemClick(int position, View view) {
        final ProductListModel.ProductResponseData data = productListModelArrayList.get(position);
        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
            return;
        }
        Log.e("In item click","clicked");
        mLastClickTime = SystemClock.elapsedRealtime();
        final Bundle bundle = new Bundle();
        bundle.putInt("Id",data.getId());
        bundle.putInt("amount",data.getAmount());
        bundle.putString("name",data.getName());
        bundle.putString("imageUrl",data.getImage());

        final Fragment punjabiFragment = new FoodDetailFragment();
        punjabiFragment.setArguments(bundle);
        addFragment(this, punjabiFragment, true);



    }
}
