package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.widgets.AppImageView;

public class FoodTypeFragment extends BaseFragment implements View.OnClickListener{

    private CardView foodTypeCardViewGujarati;
    private CardView foodTypeCardViewPunjabi;
    private CardView foodTypeCardViewJain;
    private AppImageView foodTypeGujarati;
    private String name;
    private String foodService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_type, container, false);
    }

    @Override
    protected void initView(View view) {
        /*if(getArguments()!=null){
            foodService = getArguments().getString("foodService");
        }*/
        foodTypeCardViewGujarati = (CardView) view.findViewById(R.id.fragment_food_type_cv_gujarati);
        foodTypeCardViewPunjabi = (CardView) view.findViewById(R.id.fragment_food_type_cv_punjabi);
        foodTypeCardViewJain = (CardView) view.findViewById(R.id.fragment_food_type_cv_jain);
        foodTypeGujarati = (AppImageView) view.findViewById(R.id.fragment_food_gujarati);
        foodTypeGujarati.setGradient(true);

        foodTypeCardViewGujarati.setOnClickListener(this);
        foodTypeCardViewPunjabi.setOnClickListener(this);
        foodTypeCardViewJain.setOnClickListener(this);

        foodTypeGujarati.loadImage("https://drive.google.com/open?id=0B3TjsORMi5wKTi1jSktGdlVZVWs");
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(getString(R.string.food));
        SecondActivity.getInstance().hideBackButton();
    }

    @Override
    public void onClick(View view) {
        final Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.fragment_food_type_cv_gujarati:
                bundle.putString(Constant.CARD_NAME,getString(R.string.gujarathi_thali));
                final Fragment gujaratiFragment = new FoodDetailFragment();
                gujaratiFragment.setArguments(bundle);
                addFragment(this, gujaratiFragment, true);
                /*final Fragment orderFoodFragment = new OrderFoodFragment();
                orderFoodFragment.setArguments(bundle);
                addFragment(this, orderFoodFragment, true);*/
                break;
            case R.id.fragment_food_type_cv_punjabi:
                bundle.putString(Constant.CARD_NAME,getString(R.string.punjabi_thali));
                final Fragment punjabiFragment = new FoodDetailFragment();
                punjabiFragment.setArguments(bundle);
                addFragment(this, punjabiFragment, true);
                break;
            case R.id.fragment_food_type_cv_jain:
                bundle.putString(Constant.CARD_NAME,getString(R.string.jain_thali));
                final Fragment jainFragment = new FoodDetailFragment();
                jainFragment.setArguments(bundle);
                addFragment(this, jainFragment, true);
                break;
        }
    }
}
