package com.rudraksh.food.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.rudraksh.food.R;
import com.rudraksh.food.activity.MainActivity;
import com.rudraksh.food.models.ExtraFoodModel;
import com.rudraksh.food.models.UserModel;
import com.rudraksh.food.models.UserResponseModel;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.Logger;
import com.rudraksh.food.utils.SendMail;
import com.rudraksh.food.utils.Utils;
import com.rudraksh.food.webservices.RestClient;
import com.rudraksh.food.webservices.RetrofitCallback;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Call;

/**
 * Created by dell3 on 19/4/16.
 */
public class OrderFoodFragment extends BaseFragment implements View.OnClickListener {

    private EditText orderFoodETUserName;
    private EditText orderFoodETMobileNo;
    private EditText orderFoodETAddress1;
    private EditText orderFoodETAdrress2;
    private EditText orderFoodETEmail;

    private Button orderFoodBtnOrder;
    private TextView orderFoodTVTotalBill;
    private CoordinatorLayout orderFoodCordinatorLayout;

    private String pincode;
    private String totalBill;
    private String totalQuantity;
    private String selectedOrderFoodName;
    private String orderFormatTime;

    private int productId;
    private int thaliCount;
    private int have_extra_product;
    private SendMail sendMailToUser;
    private SendMail sendMailForMukesh;

    private ArrayList<ExtraFoodModel.ExtraFoodResponseModel> dataFromFoodDetailFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_food, container, false);
    }

    @Override
    protected void initView(View view) {
        if (getArguments() != null) {
            totalBill = getArguments().getString("TotalBill");
            totalQuantity = getArguments().getString(Constant.TOTAL_QUANTITY);
            selectedOrderFoodName = getArguments().getString(Constant.CARD_NAME);
            pincode = getArguments().getString("pincode");
            dataFromFoodDetailFragment = getArguments().getParcelableArrayList("Data");
            productId = getArguments().getInt("productId");
            thaliCount = getArguments().getInt("thali count");
            have_extra_product = getArguments().getInt("have_extra");


        }
        orderFoodETUserName = (EditText) view.findViewById(R.id.fragment_order_edt_user_name);
        orderFoodETMobileNo = (EditText) view.findViewById(R.id.fragment_order_edt_mobile_no);
        orderFoodETAddress1 = (EditText) view.findViewById(R.id.fragment_order_edt_address1);
        orderFoodETAdrress2 = (EditText) view.findViewById(R.id.fragment_order_edt_address2);
        orderFoodETEmail = (EditText) view.findViewById(R.id.fragment_order_edt_email);

        orderFoodBtnOrder = (Button) view.findViewById(R.id.fragment_order_food_btn_order);
        orderFoodTVTotalBill = (TextView) view.findViewById(R.id.order_food_tv_total_bill);
        orderFoodCordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.fargment_order_food_coordinatorLayout);
//        orderFoodTVOrderDateTime = (TextView) view.findViewById(R.id.fragment_order_tv_order_date);
//        orderFoodEdtOrderDate = (EditText) view.findViewById(R.id.fragment_order_edt_order_date);

//        orderFoodEdtOrderDate.setOnClickListener(this);
        orderFoodBtnOrder.setOnClickListener(this);
        if (!TextUtils.isEmpty(totalBill)) {
            orderFoodTVTotalBill.setText(getString(R.string.Rs) + String.valueOf(totalBill));
        }
    }

    @Override
    protected void initToolbar() {
        MainActivity.getInstance().setActionBarTitle(getString(R.string.gujarathi_thali));
        MainActivity.getInstance().showBackButton();
        MainActivity.getInstance().getShareImageView().setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_order_food_btn_order:
                try {
                    checkFoodOrderUserValidation();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            /*case R.id.fragment_order_edt_order_date:
//                openOrderDateTime();
                break;*/
        }
    }

    private void checkFoodOrderUserValidation() throws JSONException {
        final String userName = orderFoodETUserName.getText().toString();
        final String mobileNo = orderFoodETMobileNo.getText().toString();
        final String address1 = orderFoodETAddress1.getText().toString();
        final String address2 = orderFoodETAdrress2.getText().toString();
        final String email = orderFoodETEmail.getText().toString();
//        final String orderTime = orderFoodTVOrderDateTime.getText().toString();

        if (!TextUtils.isEmpty(userName)) {
            if (!TextUtils.isEmpty(mobileNo)) {
                if (Utils.isValidMobile(mobileNo)) {
                    if (TextUtils.isEmpty(email)) {
                        Logger.snackBar(orderFoodCordinatorLayout, getActivity(), getString(R.string.address_empty));
                    }
                    if (!TextUtils.isEmpty(email)) {
                        if (Utils.isEmailValid(email)) {
                            if (!TextUtils.isEmpty(address1)) {

                                UserModel usermodel = setUserData(userName, mobileNo, address1, address2, pincode);
                                doSignUp(usermodel);
                            } else {
                                Logger.snackBar(orderFoodCordinatorLayout, getActivity(), getString(R.string.address_empty));
                            }
                        } else {
                            Logger.snackBar(orderFoodCordinatorLayout, getActivity(), getString(R.string.email_not_valid));
                        }
                    }

                } else {
                    Logger.snackBar(orderFoodCordinatorLayout, getActivity(), getString(R.string.enter_valid_mobile));
                }
            } else {
                Logger.snackBar(orderFoodCordinatorLayout, getActivity(), getString(R.string.enter_mobile));
            }
        } else {
            Logger.snackBar(orderFoodCordinatorLayout, getActivity(), getString(R.string.enter_name));
        }
    }

    private UserModel setUserData(String userName, String mobileNo, String address1, String address2, String pincode) throws JSONException {
        UserModel usermodel = new UserModel();
        usermodel.setName(userName);
        usermodel.setMobile(mobileNo);
        usermodel.setPincode(pincode);
        usermodel.setAddress(address1);
        usermodel.setAddress2(address2);
        usermodel.setAmount(Integer.parseInt(totalBill));
        usermodel.setProduct_id(productId);
        usermodel.setProduct_count(thaliCount);
        if (have_extra_product == 1) {
            usermodel.setHave_extra(have_extra_product);
            Log.e("have_extra", String.valueOf(have_extra_product));
            JsonObject myJson = new JsonObject();
            for (int i = 0; i < dataFromFoodDetailFragment.size(); i++) {
                Log.e("count ", String.valueOf(dataFromFoodDetailFragment.get(i).getItem_count()));
                Log.e("name", dataFromFoodDetailFragment.get(i).getExtra_food_name());
                Log.e("amount", String.valueOf(dataFromFoodDetailFragment.get(i).getAmount()));
                myJson.addProperty(String.valueOf(dataFromFoodDetailFragment.get(i).getId()), dataFromFoodDetailFragment.get(i).getItem_count());
            }
            usermodel.setExtra_products(myJson.toString());
            Log.e("myJson", String.valueOf(myJson));
        } else {
            usermodel.setHave_extra(have_extra_product);
            Log.e("have_extra", String.valueOf(have_extra_product));
        }
        return usermodel;
    }

    private void doSignUp(final UserModel userDetail) {
        try {
            final ProgressDialog dialog = Logger.showProgressDialog(getContext());
            final Call<UserResponseModel> userModelCall = RestClient.getInstance().getApiInterface().signUp(userDetail);
            userModelCall.enqueue(new RetrofitCallback<UserResponseModel>(getContext(), dialog) {
                @Override
                public void onSuccess(UserResponseModel userModel) {
                    if (userModel.isresponse()) {
                        Log.e("message", userModel.getMessage().toString());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm a");
                        final String formattedDate = dateFormat.format(new Date()).toString();
                        /*if (formattedDate.contains("am")) {
                            sm = new SendMail(getActivity(), "sraju432@gmail.com", "Order for " + totalQuantity + " Lunch Pack",
                                    "Name : " + userDetail.getName() + "\n" +
                                            " Mobile No " + userDetail.getMobile() + "\n" + " Address: " + userDetail.getAddress() + "\n" +
                                            "Ordered Detail : ", " Your Order is " + " \n" + selectedOrderFoodName + " :" + thaliCount + "\n" + sb.toString() +
                                    "Total Bill  : " + totalBill);
                        } else if (formattedDate.contains("pm")) {
                            sm = new SendMail(getActivity(), "sraju432@gmail.com", "Order for " + totalQuantity + " Dinner Pack",
                                    "Name : " + userDetail.getName() + "\n" +
                                            " Mobile No " + userDetail.getMobile() + "\n" + " Address: " + userDetail.getAddress());
                        }

                        sm.execute();*/
                        orderFoodETUserName.setText("");
                        orderFoodETMobileNo.setText("");
                        orderFoodETAddress1.setText("");
                        openThankYouAlertDialog(userDetail);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void openThankYouAlertDialog(final UserModel userDetail) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage(getActivity().getString(R.string.thank_you_dialog));
        alertDialog.setPositiveButton(getActivity().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataFromFoodDetailFragment.size(); i++) {
            String name = dataFromFoodDetailFragment.get(i).getExtra_food_name();
            sb.append(name + " : ");
            String Totalcount = String.valueOf(dataFromFoodDetailFragment.get(i).getItem_count());
            sb.append(Totalcount + "\n " + " ");

        }
        sendMailForMukesh = new SendMail(getActivity(), "sraju432@gmail.com", "Order for " + totalQuantity + " Dinner Pack",
                "Name : " + userDetail.getName() + "\n" +
                        "Mobile No " + userDetail.getMobile() + "\n" + "Address: " + userDetail.getAddress() +
        "\n \n" + "Order is " + "\n" + selectedOrderFoodName + " :" + thaliCount + "\n" + sb.toString() +
                "\n \n" + "Total Bill  : " + totalBill + " Rs");
        sendMailToUser = new SendMail(getActivity(), orderFoodETEmail.getText().toString(), "Your order Detail From RudrakshFood", " Your Order is " + " \n" + selectedOrderFoodName + " :" + thaliCount + "\n" + sb.toString() +
                "\n \n" +  "Total Bill  : " + totalBill + " Rs");
        sendMailForMukesh.execute();
        sendMailToUser.execute();
        final Fragment foodTypeFragment = new FoodTypeFragment();
        addFragment(this, foodTypeFragment, true);
        alertDialog.show();
    }

}


