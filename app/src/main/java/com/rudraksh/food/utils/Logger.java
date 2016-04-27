package com.rudraksh.food.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.rudraksh.food.R;

public class Logger {
    private static String TAG = "Logger";

    public static void setTag(String TAG) {
        Logger.TAG = TAG;
    }

    /**
     * Log.e()
     *
     * @param message which is display in logcat.
     */
    public static final void e(String message) {
        Log.e(Logger.TAG, message);
    }

    /**
     * Log.v()
     *
     * @param message which is display in logcat.
     */
    public static final void v(String message) {
        Log.v(Logger.TAG, message);
    }

    /**
     * Log.w()
     *
     * @param message which is display in logcat.
     */
    public static final void w(String message) {
        Log.w(Logger.TAG, message);
    }

    /**
     * Log.d()
     *
     * @param message which is display in logcat.
     */
    public static final void d(String message) {
        Log.d(Logger.TAG, message);
    }

    /**
     * Log.i()
     *
     * @param message which is display in logcat.
     */
    public static final void i(String message) {
        Log.i(Logger.TAG, message);
    }


    /**
     * Show Default Toast
     *
     * @param context Application/Activity context
     * @param message Message which is display in toast.
     */
    public static final void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static final void snackBar(CoordinatorLayout coordinatorLayout,Context context, String message) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    /**
     * Show Default dialog.
     *
     * @param context Application/Activity Context for creating dialog.
     * @param title   Title of dialog
     * @param message Message of dialog
     */
    public static final void dialog(Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getString(android.R.string.ok), new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * Show default dialog
     *
     * @param context Application/Activity Context
     * @param message Message of dialog
     */
    public static final void dialog(Context context, String message) {
        dialog(context, context.getString(R.string.app_name), message);
    }

    /**
     * dismiss progress dialog if is visible.
     *
     * @param progressDialog
     */
    public static final void dismissProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }



    /**
     * Display progress dialog
     *
     * @param context
     * @return ProgressDialog
     */
    /*public static final ProgressDialog showProgressDialog(Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context, R.style.ProgressDialog_Theme);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        return progressDialog;
    }*/

}
