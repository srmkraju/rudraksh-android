package com.rudraksh.food.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rudraksh.food.utils.Logger;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dell8 on 19/5/16.
 */
public abstract class RetrofitCallback<T> implements Callback<T> {
    private ProgressDialog progressDialog;
    private Context context;
    public RetrofitCallback(Context c,ProgressDialog dialog)
    {
        progressDialog = dialog;
        context = c;
    }
    public abstract void onSuccess(T arg0);

    @Override
    public void onResponse(Response<T> response, Retrofit retrofit)
    {
        Logger.dismissProgressDialog(progressDialog);

        if(response.isSuccess())
        {
            onSuccess(response.body());
        }
        else {
            try
            {
                final RetrofitModel model = new Gson().fromJson(response.errorBody().string(), RetrofitModel.class);
                if(model != null && !TextUtils.isEmpty(model.getMessage()))
                {
                    Logger.dialog(context,model.getMessage());
                }
                else
                {
                    Logger.dialog(context,response.errorBody().toString());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                Logger.dialog(context,response.errorBody().toString());
            }
        }
    }
    @Override
    public void onFailure(Throwable error) {
        String errorMsg;
        if (error!= null) {
            if (error instanceof SocketTimeoutException) {
                errorMsg = "Connection Timeout";
            } else if (error instanceof UnknownHostException || error instanceof ConnectException) {
                errorMsg = "No Internet Connection Available";
            } else if (error instanceof JSONException) {
                errorMsg = "Unable to parse response from server";
            } else if (error instanceof JsonSyntaxException) {
                errorMsg = "Unable to parse response from server";
            }

            else {
                errorMsg = "Something went wrong.Please try again later.";
            }
        } else {
            errorMsg = "Something went wrong.Please try again later.";
        }
        Logger.dismissProgressDialog(progressDialog);
        Logger.dialog(context, errorMsg);
    }

}
