package com.rudraksh.food.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.service.NotifyAlarmService;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.Logger;

import java.util.Calendar;
import java.util.zip.CheckedOutputStream;

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
    //extras
    private ImageView extraRotiIVAdd;
    private ImageView extraRotiIVMinus;
    private TextView extraRotiTVTotal;

    private ImageView extraVegCurryIVAdd;
    private ImageView extraVegCurryIVMinus;
    private TextView extraVegCurryTVTotal;

    private ImageView extraDalIVAdd;
    private ImageView extraDalIVMinus;
    private TextView extraDalTVTotal;

    private ImageView extraPulsesIVAdd;
    private ImageView extraPulsesIVMinus;
    private TextView extraPulsesTVTotal;

    private ImageView extraRiceIVAdd;
    private ImageView extraRiceIVMinus;
    private TextView extraRiceTVTotal;

    private ImageView extraButterMilkIVAdd;
    private ImageView extraButterMilkIVMinus;
    private TextView extraButterMilkTVTotal;
    private TextView extraFoodTVTotal;
    private EditText foodDetailEdtPinCodeCheck;
    private Button foodDetailBtnCheck;
    private LinearLayout foodDetialLinearLayoutPinCheck;
    private LinearLayout foodDetailLinearLayoutExtras;
    private LinearLayout foodDetailLinearLayoutAddMinus;
    private RelativeLayout foodDetailRelativeLayoutTotalBill;
    private RelativeLayout foodDetailRelativeLayoutOrder;

    private int count;
    private int thaliPrice;
    private CoordinatorLayout foodDetailCoordinatorLayout;
    private String oneThaliPrice;
    private LinearLayout fragmentFoodLinearLayout;
    private PendingIntent pendingIntent;

    //extraPrice
    private int extraGujaratiRoti;
    private int extraGujaratiRotiCount;
    private int extraGujaratiVegCurry;
    private int extraGujaratiVegCurryCount;
    private int extraGujaratiDal;
    private int extraGujaratiDalCount;
    private int extraGujaratiPulses;
    private int extraGujaratiPulseCount;
    private int extraGujaratiRice;
    private int extraGujaratiRiceCount;
    private int extraGujaratiButtermilk;
    private int extraGujaratiButtermilkCount;

    private int extraPunjabiRoti;
    private int extraPunjabiVegCurry;
    private int extraPunjabiDal;
    private int extraPunjabiPulses;
    private int extraPunjabiRice;
    private int extraPunjabiButtermilk;

    private int extraPunjabiRotiCount;
    private int extraPunjabiVegCurryTotal;
    private int extraPunjabiVegCurryCount;
    private int extraPunjabiDalCount;
    private int extraPunjabiPulseCount;
    private int extraPunjabiRiceCount;
    private int extraPunjabiButtermilkCount;

    private int extraJainRoti;
    private int extraJainVegCurry;
    private int extraJainDal;
    private int extraJainPulses;
    private int extraJainrice;
    private int extraJainButtermilk;

    private int extraJainRotiCount;
    private int extraJainVegCurryTotal;
    private int extraJainVegCurryCount;
    private int extraJainDalCount;
    private int extraJainPulseCount;
    private int extraJainRiceCount;
    private int extraJainButtermilkCount;

    private int extraFoodTotal;
    private int extraGujaratiVegCurryTotal;
    private int extraGujaratiRotiTotal;
    private int gujaratiThali;

    private int total;


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
        foodDetailEdtPinCodeCheck = (EditText) view.findViewById(R.id.fragment_food_edt_check_availability);
        foodDetailBtnCheck = (Button) view.findViewById(R.id.fragment_food_btn_check);
        foodDetialLinearLayoutPinCheck = (LinearLayout) view.findViewById(R.id.fragment_order_ll_check_pincode);
        foodDetailLinearLayoutExtras = (LinearLayout) view.findViewById(R.id.fragment_food_ll_parent_extras);
        foodDetailLinearLayoutAddMinus = (LinearLayout) view.findViewById(R.id.fragment_food_ll_parent_add_minus);
        foodDetailRelativeLayoutTotalBill = (RelativeLayout) view.findViewById(R.id.fragment_order_rl_total_bill);
        foodDetailRelativeLayoutOrder = (RelativeLayout) view.findViewById(R.id.food_detail_rl_order_now) ;

        //extras
        extraRotiIVAdd = (ImageView) view.findViewById(R.id.extra_food_IV_roti_add);
        extraRotiIVMinus = (ImageView) view.findViewById(R.id.extra_food_IV_roti_minus);
        extraRotiTVTotal = (TextView) view.findViewById(R.id.extra_food_TV_roti_total);

        extraVegCurryIVAdd = (ImageView) view.findViewById(R.id.extra_food_IV_vegcurry_add);
        extraVegCurryIVMinus = (ImageView) view.findViewById(R.id.extra_food_IV_vegcurry_minus);
        extraVegCurryTVTotal = (TextView) view.findViewById(R.id.extra_food_TV_vegcurry_total);

        extraDalIVAdd = (ImageView) view.findViewById(R.id.extra_food_IV_dal_add);
        extraDalIVMinus = (ImageView) view.findViewById(R.id.extra_food_IV_dal_minus);
        extraDalTVTotal = (TextView) view.findViewById(R.id.extra_food_TV_dal_total);

        extraPulsesIVAdd = (ImageView) view.findViewById(R.id.extra_food_IV_pulse_add);
        extraPulsesIVMinus = (ImageView) view.findViewById(R.id.extra_food_IV_pulse_minus);
        extraPulsesTVTotal = (TextView) view.findViewById(R.id.extra_food_TV_pulse_toatl);

        extraRiceIVAdd = (ImageView) view.findViewById(R.id.extra_food_IV_rice_add);
        extraRiceIVMinus = (ImageView) view.findViewById(R.id.extra_food_IV_rice_minus);
        extraRiceTVTotal = (TextView) view.findViewById(R.id.extra_food_TV_rice_total);

        extraButterMilkIVAdd = (ImageView) view.findViewById(R.id.extra_food_IV_buttermilk_add);
        extraButterMilkIVMinus = (ImageView) view.findViewById(R.id.extra_food_IV_buttermilk_minus);
        extraButterMilkTVTotal = (TextView) view.findViewById(R.id.extra_food_TV_buttermilk_total);

        extraFoodTVTotal = (TextView) view.findViewById(R.id.order_food_extra_TV_total);

        foodIVMinus.setOnClickListener(this);
        foodIVPlus.setOnClickListener(this);
        foodDetailOrderButton.setOnClickListener(this);
        foodDetailBtnCheck.setOnClickListener(this);

        //extras on click
        extraRotiIVAdd.setOnClickListener(this);
        extraRotiIVMinus.setOnClickListener(this);

        extraVegCurryIVAdd.setOnClickListener(this);
        extraVegCurryIVMinus.setOnClickListener(this);

        extraDalIVAdd.setOnClickListener(this);
        extraDalIVMinus.setOnClickListener(this);

        extraPulsesIVAdd.setOnClickListener(this);
        extraPulsesIVMinus.setOnClickListener(this);

        extraRiceIVAdd.setOnClickListener(this);
        extraRiceIVMinus.setOnClickListener(this);

        extraButterMilkIVAdd.setOnClickListener(this);
        extraButterMilkIVMinus.setOnClickListener(this);

        //foodTVTotal.setText(getString(R.string.Rs));
        //oneThaliPrice = foodTVTotalQuantity.getText().toString();
        if (getArguments() != null) {
            selectedFoodName = getArguments().getString(Constant.CARD_NAME);
            if (selectedFoodName.equalsIgnoreCase(getString(R.string.gujarathi_thali))) {
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.roties));
                count = 0;
                thaliPrice = 80;
                extraGujaratiRoti = 10;
                extraGujaratiVegCurry = 50;
                extraGujaratiDal = 50;
                extraGujaratiPulses = 50;
                extraGujaratiRice = 40;
                extraGujaratiButtermilk=10;

                extraGujaratiRotiCount = 0;
                extraGujaratiVegCurryTotal = 0;
                extraGujaratiVegCurryCount = 0;
                extraGujaratiDalCount = 0;
                extraGujaratiPulseCount = 0;
                extraGujaratiRiceCount = 0;
                extraGujaratiButtermilkCount = 0;

            } else if (selectedFoodName.equalsIgnoreCase(getString(R.string.punjabi_thali))) {
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.paratha));
                count = 0;
                thaliPrice = 110;
                extraPunjabiRoti = 10;
                extraPunjabiVegCurry = 60;
                extraPunjabiDal = 60;
                extraPunjabiPulses = 50;
                extraPunjabiRice = 40;
                extraPunjabiButtermilk=10;

                extraPunjabiRotiCount = 0;
                extraPunjabiVegCurryTotal = 0;
                extraPunjabiVegCurryCount = 0;
                extraPunjabiDalCount = 0;
                extraPunjabiPulseCount = 0;
                extraPunjabiRiceCount = 0;
                extraPunjabiButtermilkCount = 0;
            } else if (selectedFoodName.equalsIgnoreCase(getString(R.string.jain_thali))) {
                foodDetailImageView.setImageResource(R.drawable.gujarathi_thali);
                foodDetailTextViewRoties.setText(getString(R.string.paratha));
                count = 0;
                thaliPrice = 80;
                extraJainRoti = 10;
                extraJainVegCurry = 50;
                extraJainDal = 50;
                extraJainPulses = 50;
                extraJainrice = 40;
                extraJainButtermilk=10;

                extraJainRotiCount = 0;
                extraJainVegCurryTotal = 0;
                extraJainVegCurryCount = 0;
                extraJainDalCount = 0;
                extraJainPulseCount = 0;
                extraJainRiceCount = 0;
                extraJainButtermilkCount = 0;
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
            case R.id.fragment_food_btn_check:
                final String pinCode = foodDetailEdtPinCodeCheck.getText().toString();
                if(!TextUtils.isEmpty(pinCode)){
                    if(pinCode.equalsIgnoreCase("380009")){
                        Logger.snackBar(foodDetailCoordinatorLayout,getActivity(),getString(R.string.available_food));
                        foodDetailLinearLayoutAddMinus.setVisibility(View.VISIBLE);
                        foodDetailLinearLayoutExtras.setVisibility(View.VISIBLE);
                    } else{
                        foodDetailLinearLayoutAddMinus.setVisibility(View.GONE);
                        foodDetailLinearLayoutExtras.setVisibility(View.GONE);
                        Logger.snackBar(foodDetailCoordinatorLayout,getActivity(),getString(R.string.not_available_pin_code));
                    }
                }
                break;
            case R.id.fragment_food_detail_btn_order:
                //setAlarmService();
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
               /* count = count + 1;
                final int convertPlusPrice = thaliPrice * count;*/
                count =  count + 1;
                gujaratiThali = gujaratiThali * count;
                if (count > 0) {
                    if(selectedFoodName.equalsIgnoreCase(getString(R.string.gujarathi_thali))){
                        calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                        foodDetailRelativeLayoutTotalBill.setVisibility(View.VISIBLE);
                        foodDetailRelativeLayoutOrder.setVisibility(View.VISIBLE);
                    } else if(selectedFoodName.equalsIgnoreCase(getString(R.string.punjabi_thali))){
                        calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                        foodDetailRelativeLayoutTotalBill.setVisibility(View.VISIBLE);
                        foodDetailRelativeLayoutOrder.setVisibility(View.VISIBLE);
                    } else if (selectedFoodName.equalsIgnoreCase(getString(R.string.jain_thali))){
                        calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                        foodDetailRelativeLayoutTotalBill.setVisibility(View.VISIBLE);
                        foodDetailRelativeLayoutOrder.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.fragment_food_iv_minus:
                count = count - 1;
                final int convertMinusPrice = thaliPrice * count;
                if (count > -1) {
                    foodTVTotalQuantity.setText(String.valueOf(count));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.row_toolbar_iv_share:
                shareImage();
                break;
            case R.id.extra_food_IV_roti_add:
                extraGujaratiRotiCount =  extraGujaratiRotiCount + 1;
                //extraGujaratiRotiTotal = extraGujaratiRoti * extraGujaratiRotiCount;
                //total = extraGujaratiRotiTotal+extraFoodTotal;
                if (extraGujaratiRotiCount > 0) {
                    extraRotiTVTotal.setText(String.valueOf(extraGujaratiRotiCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_roti_minus:
                extraGujaratiRotiCount = extraGujaratiRotiCount - 1;
                if (extraGujaratiRotiCount > -1) {
                    extraRotiTVTotal.setText(String.valueOf(extraGujaratiRotiCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_vegcurry_add:
                extraGujaratiVegCurryCount = extraGujaratiVegCurryCount + 1;
                if (extraGujaratiVegCurryCount > 0) {
                    extraVegCurryTVTotal.setText(String.valueOf(extraGujaratiVegCurryCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_vegcurry_minus:
                extraGujaratiVegCurryCount = extraGujaratiVegCurryCount - 1;
                if (extraGujaratiVegCurryCount > -1) {
                    extraVegCurryTVTotal.setText(String.valueOf(extraGujaratiVegCurryCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                    break;
            case R.id.extra_food_IV_dal_add:
                extraGujaratiDalCount = extraGujaratiDalCount + 1;
                if(extraGujaratiDalCount > 0){
                    extraDalTVTotal.setText(String.valueOf(extraGujaratiDalCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_dal_minus:
                extraGujaratiDalCount = extraGujaratiDalCount - 1;
                if(extraGujaratiDalCount > -1){
                    extraDalTVTotal.setText(String.valueOf(extraGujaratiDalCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_pulse_add:
                extraGujaratiPulseCount = extraGujaratiPulseCount + 1;
                if(extraGujaratiPulseCount > 0){
                    extraPulsesTVTotal.setText(String.valueOf(extraGujaratiPulseCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_pulse_minus:
                extraGujaratiPulseCount = extraGujaratiPulseCount - 1;
                if(extraGujaratiPulseCount > -1){
                    extraPulsesTVTotal.setText(String.valueOf(extraGujaratiPulseCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_rice_add:
                extraGujaratiRiceCount = extraGujaratiRiceCount + 1;
                if(extraGujaratiRiceCount > 0){
                    extraRiceTVTotal.setText(String.valueOf(extraGujaratiRiceCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_rice_minus:
                extraGujaratiRiceCount = extraGujaratiRiceCount - 1;
                if(extraGujaratiRiceCount > -1){
                    extraRiceTVTotal.setText(String.valueOf(extraGujaratiRiceCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_buttermilk_add:
                extraGujaratiButtermilkCount = extraGujaratiButtermilkCount + 1;
                if(extraGujaratiButtermilkCount > 0){
                    extraButterMilkTVTotal.setText(String.valueOf(extraGujaratiButtermilkCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
            case R.id.extra_food_IV_buttermilk_minus:
                extraGujaratiButtermilkCount = extraGujaratiButtermilkCount - 1;
                if(extraGujaratiButtermilkCount > -1){
                    extraButterMilkTVTotal.setText(String.valueOf(extraGujaratiButtermilkCount));
                    calculateTotal(count,extraGujaratiRotiCount,extraGujaratiVegCurryCount,extraGujaratiDalCount,extraGujaratiPulseCount,extraGujaratiRiceCount,extraGujaratiButtermilkCount);
                }
                break;
        }
    }

    private void calculateTotal(final int thaliCount, final int gujaratiRotiCount, final int gujaratiVegCurryCount, final int gujaratiDalCount,
                                final int gujaratiPulseCount, final int guajaratiRiceCount, final int butterMilkCount){
        if(thaliCount > 0){
            final int thaliTotal = thaliPrice * thaliCount;
            foodTVTotalQuantity.setText(String.valueOf(thaliCount));
            //total = convertPlusPrice;
            total = (thaliTotal) + (gujaratiRotiCount*extraGujaratiRoti) + (gujaratiVegCurryCount*extraGujaratiVegCurry) +
                    (gujaratiDalCount*extraGujaratiDal) + (gujaratiPulseCount*extraGujaratiPulses) + (guajaratiRiceCount*extraGujaratiRice) +
                    (butterMilkCount*extraGujaratiButtermilk);

            foodDetailTVTotalPrice.setText(String.valueOf(thaliTotal));
            extraFoodTVTotal.setText(String.valueOf(total));
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
