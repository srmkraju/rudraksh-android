package com.rudraksh.food.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    /**
     * Check that network mobile data or 3G is available or not.
     *
     * @param context
     * @return boolean
     */
    public static boolean DEBUG = false;

    public static final boolean isConnectedToInternet(Context context) {
        if (context != null) {
            final ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mgr != null) {

                final NetworkInfo mobileInfo = mgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                final NetworkInfo wifiInfo = mgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (wifiInfo != null && wifiInfo.isAvailable() && wifiInfo.isAvailable() && wifiInfo.isConnected()) {

                    final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    final WifiInfo wifiInfoStatus = wifiManager.getConnectionInfo();
                    final SupplicantState supState = wifiInfoStatus.getSupplicantState();

                    if (String.valueOf(supState).equalsIgnoreCase("COMPLETED") || String.valueOf(supState).equalsIgnoreCase("ASSOCIATED")) {
                        // WiFi is connected
                        return true;
                    }
                }

                if (mobileInfo != null && mobileInfo.isAvailable() && mobileInfo.isConnected()) {
                    // Mobile Network is connected
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check email is valid or not.
     *
     * @param email
     * @return boolean
     */
    public static final boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static final boolean isValidMobile(String mobile) {
        String regexStr = "^[0-9]{10}$";
        if (mobile.matches(regexStr)) {
            return true;
        }
        return false;
    }

    /**
     * Check String is empty or not.
     *
     * @param message
     * @return boolean
     */
    public static final boolean isStringEmpty(String message) {
        boolean isValid = false;
        if (message.isEmpty()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Store string value in shared preference.
     *
     * @param context
     * @param key
     * @param value
     */
    public static final void storeString(Context context, String key, String value) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Store integer value in shared preference.
     *
     * @param context
     * @param key
     * @param value
     */
    public static final void storeInt(Context context, String key, int value) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Store integer value in shared preference.
     *
     * @param context
     * @param key
     * @param value
     */
    public static final void storeLong(Context context, String key, long value) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Get string value from shared preference.
     *
     * @param context
     * @param key
     * @return String
     */
    public static final String getString(Context context, String key) {
        String data;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        data = preferences.getString(key, "");
        editor.commit();
        return data;
    }

    /**
     * Get integer value from shared preference.
     *
     * @param context
     * @param key
     * @return integer
     */
    public static final int getInt(Context context, String key) {
        int data;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        data = preferences.getInt(key, 0);
        editor.commit();
        return data;
    }

    /**
     * Get integer value from shared preference.
     *
     * @param context
     * @param key
     * @return long
     */
    public static final long getLong(Context context, String key) {
        long data;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        data = preferences.getLong(key, 0);
        editor.commit();
        return data;
    }

    /**
     * Store boolean value from shared preference.
     *
     * @param context
     * @param key
     * @param value
     */
    public static final void storeBoolean(Context context, String key, boolean value) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Get boolean from shared preference with specified key.
     *
     * @param context
     * @param key
     * @return boolean
     */
    public static final boolean getBoolean(Context context, String key) {
        boolean data;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        data = preferences.getBoolean(key, false);
        editor.commit();
        return data;
    }

    /**
     * Hide keyboard if it is visible
     *
     * @param context
     */
    public static final void hideSoftKeyboard(Context context) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (((Activity) context).getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * Device is tablet or not.
     *
     * @param context
     * @return boolean
     */
    public static final boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * Get unique deviceId
     *
     * @param context
     * @return String
     */
    public static final String getUniqueDeviceId(Context context) {

        String deviceId = null;
        // 1 compute IMEI
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = TelephonyMgr.getDeviceId(); // Requires // READ_PHONE_STATE

        if (deviceId == null) {
            // 2 android ID - unreliable
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        if (deviceId == null) {
            // 3 compute DEVICE ID
            deviceId = "35"
                    + // we make this look like a valid IMEI
                    Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
                    + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10; // 13
            // digits
        }
        return deviceId;
    }

    public static boolean hasCallingFeature(Context context) {
        boolean flag;
        final TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getPhoneType()) {
            case TelephonyManager.PHONE_TYPE_CDMA:
                flag = true;
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        return flag;
    }

    /*public static boolean checkPlayServices(Activity activity) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, Constant.PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Logger.i("This device is not supported.");
                activity.finish();
            }
            return false;
        }
        return true;
    }*/

    public static final String getGalleryImagePath(Uri uri,Context context){
        String result = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(column_index);
        }
        cursor.close();
        return result;
    }

}
