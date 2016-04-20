package com.rudraksh.food.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;

/**
 * Created by Raju on 4/16/2016.
 */
public class FoodDetailFragment extends BaseFragment implements View.OnClickListener {
    private String selectedFoodName;
    private ImageView foodDetailImageView;
    private TextView foodDetailTextViewRoties;
    private Button foodDetailOrderButton;
    private ImageView foodIVPlus;
    private ImageView foodIVMinus;
    private TextView foodTVTotal;
    private int count = 1;
    private String oneThaliPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_detail, container, false);
    }

    @Override
    protected void initView(View view) {
        foodDetailImageView = (ImageView) view.findViewById(R.id.fragment_food_detail_iv);
        foodDetailTextViewRoties = (TextView) view.findViewById(R.id.fragment_food_detail_tv_roties);
        foodDetailOrderButton = (Button) view.findViewById(R.id.fragment_food_detail_btn_order);
        foodIVPlus = (ImageView) view.findViewById(R.id.fragment_food_iv_plus);
        foodIVMinus = (ImageView) view.findViewById(R.id.fragment_food_iv_minus);
        foodTVTotal = (TextView) view.findViewById(R.id.fragment_food_tv_total);

        foodIVMinus.setOnClickListener(this);
        foodIVPlus.setOnClickListener(this);
        foodDetailOrderButton.setOnClickListener(this);
        //foodTVTotal.setText(getString(R.string.Rs));
        oneThaliPrice = foodTVTotal.getText().toString();
        if(getArguments()!=null){
            selectedFoodName = getArguments().getString(Constant.CARD_NAME);
            if(selectedFoodName.equalsIgnoreCase(getString(R.string.gujarathi_thali))){
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.roties));
            } else if(selectedFoodName.equalsIgnoreCase(getString(R.string.punjabi_thali))){
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.paratha));
            } else if(selectedFoodName.equalsIgnoreCase(getString(R.string.jain_thali))){
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.paratha));
            }
        }
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(selectedFoodName);
        SecondActivity.getInstance().showBackButton();
        SecondActivity.getInstance().getShareImageView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        final Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.fragment_food_detail_btn_order:
                final Fragment orderFoodFragment = new OrderFoodFragment();
                orderFoodFragment.setArguments(bundle);
                addFragment(this, orderFoodFragment, true);
                break;
            case R.id.fragment_food_iv_plus:
                count = count+1;
                final int price = Integer.parseInt(oneThaliPrice);
                int pric = price*count;
                foodTVTotal.setText(String.valueOf(pric));
                String pf = String.valueOf(pric);
                Log.e("TAG", "price " + pf);
                break;
            case R.id.fragment_food_iv_minus:
                break;
        }
    }
}
