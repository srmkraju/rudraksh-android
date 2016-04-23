package com.rudraksh.food.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.Logger;

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
    private TextView foodDetailTVTotalPrice;
    private TextView foodTVTotalQuantity;
    private int count;
    private int thaliPrice;
    private CoordinatorLayout foodDetailCoordinatorLayout;
    private String oneThaliPrice;
    private LinearLayout fragmentFoodLinearLayout;

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
        foodTVTotalQuantity = (TextView) view.findViewById(R.id.fragment_food_tv_total_quantity);
        foodDetailTVTotalPrice = (TextView) view.findViewById(R.id.fragment_food_tv_total_price);
        foodDetailCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.fargment_food_detail_coordinatorLayout);
        fragmentFoodLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_foof_detail_ll);

        foodIVMinus.setOnClickListener(this);
        foodIVPlus.setOnClickListener(this);
        foodDetailOrderButton.setOnClickListener(this);

        //foodTVTotal.setText(getString(R.string.Rs));
        //oneThaliPrice = foodTVTotalQuantity.getText().toString();
        if (getArguments() != null) {
            selectedFoodName = getArguments().getString(Constant.CARD_NAME);
            if (selectedFoodName.equalsIgnoreCase(getString(R.string.gujarathi_thali))) {
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.roties));
                count = 0;
                thaliPrice = 70;
            } else if (selectedFoodName.equalsIgnoreCase(getString(R.string.punjabi_thali))) {
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.paratha));
                count = 0;
                thaliPrice = 100;
            } else if (selectedFoodName.equalsIgnoreCase(getString(R.string.jain_thali))) {
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.paratha));
                count = 0;
                thaliPrice = 70;
            }
        }
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(selectedFoodName);
        SecondActivity.getInstance().showBackButton();
        SecondActivity.getInstance().getShareImageView().setVisibility(View.VISIBLE);
        SecondActivity.getInstance().getShareImageView().setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.fragment_food_detail_btn_order:
                if (!foodDetailTVTotalPrice.getText().toString().equalsIgnoreCase("0")) {
                    bundle.putString(Constant.TOTAL_BILL, foodDetailTVTotalPrice.getText().toString());
                    bundle.putString(Constant.TOTAL_QUANTITY, foodTVTotalQuantity.getText().toString());
                    bundle.putString(Constant.CARD_NAME, selectedFoodName);
                    final Fragment orderFoodFragment = new OrderFoodFragment();
                    orderFoodFragment.setArguments(bundle);
                    addFragment(this, orderFoodFragment, true);
                } else {
                    Logger.snackBar(foodDetailCoordinatorLayout, getActivity(), getString(R.string.select_items));
                }

                break;
            case R.id.fragment_food_iv_plus:
                count = count + 1;
                //final int plusprice = Integer.parseInt(thaliPrice);
                final int convertPlusPrice = thaliPrice * count;
                if (count > 0) {
                    foodTVTotalQuantity.setText(String.valueOf(count));
                    foodDetailTVTotalPrice.setText(String.valueOf(convertPlusPrice));
                }
                break;
            case R.id.fragment_food_iv_minus:
                count = count - 1;
                //final int minusprice = Integer.parseInt(oneThaliPrice);
                final int convertMinusPrice = thaliPrice * count;
                if (count > -1) {
                    foodTVTotalQuantity.setText(String.valueOf(count));
                    foodDetailTVTotalPrice.setText(String.valueOf(convertMinusPrice));
                }
                break;
            case R.id.row_toolbar_iv_share:
                shareImage();
                break;
        }
    }

    private void shareImage(){
        fragmentFoodLinearLayout.setDrawingCacheEnabled(true);
        fragmentFoodLinearLayout.buildDrawingCache();
        Bitmap bm = Bitmap.createBitmap(fragmentFoodLinearLayout.getDrawingCache());
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                bm, "Image Description", null);

        Uri uri = Uri.parse(path);
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}
