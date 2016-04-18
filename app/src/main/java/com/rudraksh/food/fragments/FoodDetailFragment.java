package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;

/**
 * Created by Raju on 4/16/2016.
 */
public class FoodDetailFragment extends BaseFragment {
    private String selectedFoodName;
    private ImageView foodDetailImageView;
    private TextView foodDetailTextViewRoties;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_detail, container, false);
    }

    @Override
    protected void initView(View view) {
        foodDetailImageView = (ImageView) view.findViewById(R.id.fragment_food_detail_iv);
        foodDetailTextViewRoties = (TextView) view.findViewById(R.id.fragment_food_detail_tv_roties);
        if(getArguments()!=null){
            selectedFoodName = getArguments().getString(Constant.CARD_NAME);
            if(selectedFoodName.equalsIgnoreCase("Gujarati Food")){
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.roties));
            } else if(selectedFoodName.equalsIgnoreCase("Punjabi Food")){
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.paratha));
            } else if(selectedFoodName.equalsIgnoreCase("Jain Food")){
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.paratha));
            }
        }
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(selectedFoodName);
        SecondActivity.getInstance().showBackButton();
    }
}
