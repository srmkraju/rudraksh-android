package com.rudraksh.food.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.activity.SecondActivity;
import com.rudraksh.food.utils.Constant;
import com.rudraksh.food.utils.Logger;
import com.rudraksh.food.utils.SendMail;
import com.rudraksh.food.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private TextView orderFoodTVOrderDateTime;
    private EditText orderFoodEdtOrderDate;
    private String totalBill;
    private String totalQuantity;
    private String selectedOrderFoodName;
    private String orderFormatTime;
    private SendMail sm;

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
        orderFoodTVOrderDateTime = (TextView) view.findViewById(R.id.fragment_order_tv_order_date);
        orderFoodEdtOrderDate = (EditText) view.findViewById(R.id.fragment_order_edt_order_date);

        orderFoodEdtOrderDate.setOnClickListener(this);
        orderFoodBtnOrder.setOnClickListener(this);
        if(!TextUtils.isEmpty(totalBill)){
            orderFoodTVTotalBill.setText(getString(R.string.Rs) + 130);
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
            case R.id.fragment_order_edt_order_date:
                openOrderDateTime();
                break;
        }
    }

    private void openOrderDateTime(){
        final Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(
                getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker,
                                  int
                                          selectedyear, int selectedmonth,
                                  int selectedday) {
                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH,
                        selectedday);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                        Locale.US);

                SimpleDateFormat sdfnew = new SimpleDateFormat("dd-MM-yyyy",
                        Locale.US);
                Date now = new Date();
                SimpleDateFormat sdfa = new SimpleDateFormat("K:mm a");
                String orderFormatTime = sdfa.format(now);
                orderFoodTVOrderDateTime.setTag(sdf.format(mcurrentDate.getTime()));
                Logger.e("Time " + orderFormatTime);
                orderFoodTVOrderDateTime.setText(sdfnew.format(mcurrentDate.getTime()) + " " +orderFormatTime);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.show();
    }

    private void checkFoodOrderUserValidation() {
        final String userName = orderFoodETUserName.getText().toString();
        final String mobileNo = orderFoodETMobileNo.getText().toString();
        final String address1 = orderFoodETAddress1.getText().toString();
        final String address2 = orderFoodETAdrress2.getText().toString();
        final String orderTime = orderFoodTVOrderDateTime.getText().toString();

        if (!TextUtils.isEmpty(userName)) {
            if (!TextUtils.isEmpty(mobileNo)) {
                if (Utils.isValidMobile(mobileNo)) {
                    if(!TextUtils.isEmpty(address1)){
                        //showAlarmAlertDialog();
                        //sendEmailWithContent(userName,mobileNo,address1,orderTime);
                        if(!TextUtils.isEmpty(orderTime)){
                            if(orderTime.contains("AM")){
                                sm = new SendMail(getActivity(), "sraju432@gmail.com", "Order for " + totalQuantity + " Lunch Pack",
                                        "Name : " + userName + "\n" +
                                                " Mobile No " + mobileNo + "\n" + " Address: " + address1);
                            } else if(orderTime.contains("PM")){
                                sm = new SendMail(getActivity(), "sraju432@gmail.com", "Order for " + totalQuantity + " Dinner Pack",
                                        "Name : " + userName + "\n" +
                                                " Mobile No " + mobileNo + "\n" + " Address: " + address1);
                            }
                        }

                        //Executing sendmail to send email
                        sm.execute();
                        orderFoodETUserName.setText("");
                        orderFoodETMobileNo.setText("");
                        orderFoodETAddress1.setText("");
                        openThankYouAlertDialog();
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

    private void sendEmailWithContent(final String userName, final String mobileNo, final String address1, final String orderTime) {
        Session session = createSessionObject();

        try {
            Message message = createMessage(userName, mobileNo, address1, orderTime,session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Message createMessage(String username, String moile, String messageBody, String orderTime,Session session) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sraju432@gmail.com", "subbaraju123"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("sraju432@gmail.com", "sraju432@gmail.com"));
        message.setSubject("hi");
        message.setText("301 setu");
        return message;
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.ssl.trust", "smtpserver");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sraju432@gmail.com", "subbaraju123");
            }
        });
    }

    private class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity(), "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void sendEmail(final String userName, final String mobileNo, final String address1, final String orderTime){
        if(Utils.isConnectedToInternet(getActivity())){
            String[] TO = {"sraju432@gmail.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            if(!TextUtils.isEmpty(orderFormatTime)){
                if(orderTime.contains("AM")){
                    Logger.e("Order AM");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + totalQuantity + "of " + selectedOrderFoodName + " for Lunch");
                } else if (orderTime.contains("PM")){
                    Logger.e("Order PM");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + totalQuantity + "of " + selectedOrderFoodName + " for Dinner");
                }
            }

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
    private void openThankYouAlertDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        // Setting Dialog Message
        alertDialog.setMessage(getActivity().getString(R.string.thank_you_dialog));
        alertDialog.setPositiveButton(getActivity().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final Fragment foodTypeFragment = new FoodTypeFragment();
        addFragment(this, foodTypeFragment, true);
        // Showing Alert Message
        alertDialog.show();
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


