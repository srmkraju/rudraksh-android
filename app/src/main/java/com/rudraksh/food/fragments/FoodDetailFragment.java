package com.rudraksh.food.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;
import com.rudraksh.food.models.ExtraFoodModel;
import com.rudraksh.food.service.NotifyAlarmService;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.Logger;
import com.rudraksh.food.webservices.RestClient;
import com.rudraksh.food.webservices.RetrofitCallback;
import com.rudraksh.food.widgets.AppImageView;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit.Call;

/**
 * Created by Raju on 4/16/2016.
 */
public class FoodDetailFragment extends BaseFragment implements View.OnClickListener {
    private String selectedFoodName;
    private AppImageView foodDetailImageView;
    private TextView foodDetailTextViewItemIncluded;


    private ImageView foodIVPlus;
    private ImageView foodIVMinus;


    private TextView foodDetailTVThaliTotalPrice;
    private TextView foodTVTotalQuantity;
    private TextView foodDetailExtraTextView;
    private TextView totalFoodAmount;


    private EditText foodDetailEdtPinCodeCheck;

    private Button foodDetailBtnCheck;
    private Button orderNow;
    private LinearLayout foodDetialLinearLayoutPinCheck;
    private LinearLayout foodDetailLinearLayoutExtras;
    private LinearLayout foodDetailLinearLayoutAddMinus;
    private RelativeLayout foodDetailRelativeLayoutTotalBill;


    private int count=0;
    private int thaliPrice;
    private int TotalAmount=0;

    private CoordinatorLayout foodDetailCoordinatorLayout;

    private LinearLayout fragmentFoodLinearLayout;
    private PendingIntent pendingIntent;



    private ArrayList<ExtraFoodModel.ExtraFoodResponseModel> extraFoodArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_detail, container, false);
    }

    @Override
    protected void initView(View view) {
        foodDetailImageView = (AppImageView) view.findViewById(R.id.fragment_food_detail_iv);
        foodDetailTextViewItemIncluded = (TextView) view.findViewById(R.id.fragment_food_detail_tv_itemsIncluded);

        foodIVPlus = (ImageView) view.findViewById(R.id.fragment_food_iv_plus);
        foodIVMinus = (ImageView) view.findViewById(R.id.fragment_food_iv_minus);
        foodTVTotalQuantity = (TextView) view.findViewById(R.id.fragment_food_tv_total_quantity);
        foodDetailExtraTextView = (TextView)view.findViewById(R.id.food_detail_extra_TV);
        foodDetailTVThaliTotalPrice = (TextView) view.findViewById(R.id.fragment_food_tv_thali_total_price);
        totalFoodAmount = (TextView)view.findViewById(R.id.order_food_extra_TV_total);
        foodDetailCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.fargment_food_detail_coordinatorLayout);

        foodDetailEdtPinCodeCheck = (EditText) view.findViewById(R.id.fragment_food_edt_check_availability);
        foodDetailBtnCheck = (Button) view.findViewById(R.id.fragment_food_btn_check);

        fragmentFoodLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_foof_detail_ll);
        foodDetialLinearLayoutPinCheck = (LinearLayout) view.findViewById(R.id.fragment_order_ll_check_pincode);
        foodDetailLinearLayoutExtras = (LinearLayout) view.findViewById(R.id.fragment_food_ll_parent_extras);
        foodDetailLinearLayoutAddMinus = (LinearLayout) view.findViewById(R.id.fragment_food_ll_parent_add_minus);
        foodDetailRelativeLayoutTotalBill = (RelativeLayout) view.findViewById(R.id.fragment_order_rl_total_bill);

        orderNow = (Button)view.findViewById(R.id.food_detail_bt_orderNow);
        getExtraFoodItem();


        orderNow.setOnClickListener(this);
        foodDetailBtnCheck.setOnClickListener(this);

        foodIVMinus.setOnClickListener(this);
        foodIVPlus.setOnClickListener(this);


        if (getArguments() != null) {
            final String imageUrl = getArguments().getString("imageUrl");
            foodDetailImageView.loadImage(Constant.BASE_URL+"uploads/"+imageUrl);
            selectedFoodName = getArguments().getString("name");
            thaliPrice = getArguments().getInt("amount");
            foodDetailTextViewItemIncluded.setText(getArguments().getString("description"));
        }
    }
    private void getExtraFoodItem() {
        try {
            final ProgressDialog dialog = com.rudraksh.food.utils.Logger.showProgressDialog(getContext());
            final Call<ExtraFoodModel> extraModelCall = RestClient.getInstance().getApiInterface().getExtraFood();
            extraModelCall.enqueue(new RetrofitCallback<ExtraFoodModel>(getContext(),dialog) {
                @Override
                public void onSuccess(ExtraFoodModel extraFoodResponse) {
                    if(extraFoodResponse.isResponse())
                    {
                        extraFoodResponse.getData().trimToSize();
                        extraFoodArrayList.addAll(extraFoodResponse.getData());
                        setExtraFoodData(extraFoodArrayList);

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setExtraFoodData(final ArrayList<ExtraFoodModel.ExtraFoodResponseModel> extraFoodArrayList) {


        for(int i =0; i<extraFoodArrayList.size(); i++){
            final int finalI = i;

            final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            final View view = layoutInflater.inflate(R.layout.row_food_detail_extra_item,null);
            final TextView extraFoodItemName = (TextView)view.findViewById(R.id.row_food_detail_extra_item_tv);
            final TextView  extraFoodItemPrice = (TextView)view.findViewById(R.id.row_food_detail_extra_item_price_tv);
            final TextView extraFoodQuantity = (TextView)view.findViewById(R.id.extra_food_TV_extra_total_quantity);
            final ImageView extraFoodPlusImage = (ImageView)view.findViewById(R.id.extra_food_IV_add) ;
            final ImageView extraFoodMinusImage = (ImageView)view.findViewById(R.id.extra_food_IV_minus);

            final int amount=   extraFoodArrayList.get(finalI).getAmount();
            extraFoodPlusImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = extraFoodArrayList.get(finalI).getItem_count()+1;
                    extraFoodQuantity.setText(String.valueOf(count));
                    extraFoodArrayList.get(finalI).setItem_count(count);
                    extraFoodItemPrice.setText("\u20B9"+" "+String .valueOf(amount*count));
                    TotalAmount = TotalAmount+amount;
                    setTotalPrice();
                }
            });
            extraFoodMinusImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = extraFoodArrayList.get(finalI).getItem_count()-1;
                    if(count<=0)
                    {
                        extraFoodQuantity.setText("0");
                        extraFoodItemPrice.setText("\u20B9"+" "+String .valueOf(amount));
                        if(extraFoodQuantity.getText().toString().equalsIgnoreCase("0") && count==0)
                        {
                            count=0;
                            extraFoodArrayList.get(finalI).setItem_count(count);
                            TotalAmount = TotalAmount-amount;
                            setTotalPrice();
                        }
                    }
                    else
                    {
                        extraFoodQuantity.setText(String.valueOf(count));
                        extraFoodArrayList.get(finalI).setItem_count(count);
                        extraFoodItemPrice.setText("\u20B9"+" "+String .valueOf(amount*count));
                        TotalAmount = TotalAmount- amount;
                        setTotalPrice();
                    }
                }
            });
            extraFoodItemName.setText( extraFoodArrayList.get(i).getExtra_food_name());
            extraFoodItemPrice.setText("\u20B9"+" "+String .valueOf(extraFoodArrayList.get(i).getAmount()));
            foodDetailLinearLayoutExtras.addView(view);
        }
    }
    private void setTotalPrice() {
        if(foodTVTotalQuantity.getText().toString().equalsIgnoreCase("0"))
        {
            Logger.snackBar(foodDetailCoordinatorLayout,getContext(),"Choose atleast One Quantity for the thali");
        }
        else
        {
            final int TotalPrice = TotalAmount+thaliPrice;
            Log.e("Total in setTotlaPrice", String.valueOf(TotalAmount));
            totalFoodAmount.setText(String.valueOf(TotalPrice));
        }

    }

    @Override
    protected void initToolbar() {
        MainActivity.getInstance().setActionBarTitle(selectedFoodName);
        MainActivity.getInstance().showBackButton();
        MainActivity.getInstance().getShareImageView().setVisibility(View.VISIBLE);
        MainActivity.getInstance().getShareImageView().setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.food_detail_bt_orderNow:
                if (!foodDetailTVThaliTotalPrice.getText().toString().equalsIgnoreCase("0")) {

                    bundle.putString(Constant.TOTAL_QUANTITY, foodTVTotalQuantity.getText().toString());
                    bundle.putString(Constant.CARD_NAME, selectedFoodName);
                    bundle.putString("pincode",foodDetailEdtPinCodeCheck.getText().toString());
                    bundle.putString("TotalBill",totalFoodAmount.getText().toString());
                    final Fragment orderFoodFragment = new OrderFoodFragment();
                    orderFoodFragment.setArguments(bundle);
                    addFragment(this, orderFoodFragment, true);
                } else {
                    Logger.snackBar(foodDetailCoordinatorLayout, getActivity(), getString(R.string.select_items));
                }
                break;
            case R.id.fragment_food_btn_check:
                final String pinCode = foodDetailEdtPinCodeCheck.getText().toString();
                if(!TextUtils.isEmpty(pinCode)){
                    if(pinCode.equalsIgnoreCase("380009")){
                        Logger.snackBar(foodDetailCoordinatorLayout,getActivity(),getString(R.string.available_food));
                        foodDetailLinearLayoutAddMinus.setVisibility(View.VISIBLE);
                        foodDetailLinearLayoutExtras.setVisibility(View.VISIBLE);
                        foodDetailExtraTextView.setVisibility(View.VISIBLE);
                        foodDetailRelativeLayoutTotalBill.setVisibility(View.VISIBLE);
                        orderNow.setVisibility(View.VISIBLE);
                    } else{
                        foodDetailLinearLayoutAddMinus.setVisibility(View.GONE);
                        foodDetailRelativeLayoutTotalBill.setVisibility(View.GONE);
                        foodDetailLinearLayoutExtras.setVisibility(View.GONE);
                        foodDetailExtraTextView.setVisibility(View.GONE);
                        Logger.snackBar(foodDetailCoordinatorLayout,getActivity(),getString(R.string.not_available_pin_code));
                    }
                }
                break;
            case R.id.fragment_food_iv_plus:
                thaliPrice = getArguments().getInt("amount");
                count =  count + 1;
                thaliPrice = thaliPrice*count;
                orderNow.setVisibility(View.VISIBLE);
                foodTVTotalQuantity.setText(String.valueOf(count));
                foodDetailTVThaliTotalPrice.setText("\u20B9"+" "+thaliPrice);
                totalFoodAmount.setText(String.valueOf(thaliPrice));

                break;
            case R.id.fragment_food_iv_minus:
                orderNow.setVisibility(View.VISIBLE);
                count = count - 1;
                if(count<0){
                    count=0;
                    foodTVTotalQuantity.setText("0");
                    foodDetailTVThaliTotalPrice.setText("0");
                    totalFoodAmount.setText("0");
                }
                else
                {

                    thaliPrice = getArguments().getInt("amount");
                    thaliPrice = thaliPrice*count;
                    foodTVTotalQuantity.setText(String.valueOf(count));
                    foodDetailTVThaliTotalPrice.setText("\u20B9"+" "+thaliPrice);
                    totalFoodAmount.setText(String.valueOf(thaliPrice));
                }
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

    private void setAlarmService(){
        Intent myIntent = new Intent(getActivity(), NotifyAlarmService.class);
        pendingIntent = PendingIntent.getService(getActivity(), 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*3600*1000, pendingIntent);
    }
}
