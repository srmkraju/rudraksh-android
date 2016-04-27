package com.rudraksh.food.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.Logger;
import com.rudraksh.food.utils.Utils;
/**
 * Created by dell3 on 19/4/16.
 */
public class OrderFoodFragment extends BaseFragment implements View.OnClickListener {

    private EditText orderFoodETUserName;
    private EditText orderFoodETMobileNo;
    private EditText orderFoodETAddress1;
    private EditText orderFoodETAdrress2;
    private Button orderFoodBtnOrder;
    private TextView orderFoodTVTotalBill;
    private CoordinatorLayout orderFoodCordinatorLayout;
    private String totalBill;
    private String totalQuantity;
    private String selectedOrderFoodName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_food, container, false);
    }

    @Override
    protected void initView(View view) {
        if(getArguments()!=null){
            totalBill = getArguments().getString(Constant.TOTAL_BILL);
            totalQuantity = getArguments().getString(Constant.TOTAL_QUANTITY);
            selectedOrderFoodName = getArguments().getString(Constant.CARD_NAME);
        }
        orderFoodETUserName = (EditText) view.findViewById(R.id.fragment_order_edt_user_name);
        orderFoodETMobileNo = (EditText) view.findViewById(R.id.fragment_order_edt_mobile_no);
        orderFoodETAddress1 = (EditText) view.findViewById(R.id.fragment_order_edt_address1);
        orderFoodETAdrress2 = (EditText) view.findViewById(R.id.fragment_order_edt_address2);
        orderFoodBtnOrder = (Button) view.findViewById(R.id.fragment_order_food_btn_order);
        orderFoodTVTotalBill = (TextView) view.findViewById(R.id.order_food_tv_total_bill);
        orderFoodCordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.fargment_order_food_coordinatorLayout);
        orderFoodBtnOrder.setOnClickListener(this);
        if(!TextUtils.isEmpty(totalBill)){
            orderFoodTVTotalBill.setText(getString(R.string.Rs) + totalBill);
        }
    }

    @Override
    protected void initToolbar() {
        SecondActivity.getInstance().setActionBarTitle(getString(R.string.gujarathi_thali));
        SecondActivity.getInstance().showBackButton();
        SecondActivity.getInstance().getShareImageView().setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_order_food_btn_order:
                checkFoodOrderUserValidation();
                break;
        }
    }

    private void checkFoodOrderUserValidation() {
        final String userName = orderFoodETUserName.getText().toString();
        final String mobileNo = orderFoodETMobileNo.getText().toString();
        final String address1 = orderFoodETAddress1.getText().toString();
        final String address2 = orderFoodETAdrress2.getText().toString();

        if (!TextUtils.isEmpty(userName)) {
            if (!TextUtils.isEmpty(mobileNo)) {
                if (Utils.isValidMobile(mobileNo)) {
                    if(!TextUtils.isEmpty(address1)){
                        showAlarmAlertDialog();
                        sendEmail(userName,mobileNo,address1);
                    } else {
                        Logger.snackBar(orderFoodCordinatorLayout,getActivity(), getString(R.string.address_empty));
                    }
                } else {
                    Logger.snackBar(orderFoodCordinatorLayout,getActivity(), getString(R.string.enter_valid_mobile));
                }
            } else {
                Logger.snackBar(orderFoodCordinatorLayout,getActivity(), getString(R.string.enter_mobile));
            }
        } else {
            Logger.snackBar(orderFoodCordinatorLayout,getActivity(), getString(R.string.enter_name));
        }
    }

    private void sendEmail(final String userName, final String mobileNo, final String address1){
        if(Utils.isConnectedToInternet(getActivity())){
            String[] TO = {"sraju432@gmail.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + totalQuantity + "of " + selectedOrderFoodName);
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Name : " + userName + "\n" +
                    " Mobile No " + mobileNo + "\n" + " Address1 " + address1 );

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                getActivity().finish();
            }
            catch (android.content.ActivityNotFoundException ex) {
                Logger.snackBar(orderFoodCordinatorLayout,getActivity(),getString(R.string.no_emial_client));
            }
        } else{
            Logger.snackBar(orderFoodCordinatorLayout,getActivity(),getString(R.string.connect_to_internet));
        }
        //openWhatsappContact("8140113954");

    }

    private void openWhatsappContact(String number) {

        final Uri uri = Uri.parse("smsto:" + number);
        final Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }

    private void showAlarmAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Dialog");
        builder.setMessage("Do you want to set Alarm everyday to order food?");
        builder.setPositiveButton(getString(R.string.yes), null);
        builder.setNegativeButton(getString(R.string.cancel), null);
        builder.show();
    }
}


