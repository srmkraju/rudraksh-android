package com.rudraksh.food.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.rudraksh.food.R;
import com.rudraksh.food.utils.Logger;

/**
 * Created by dell3 on 24/4/16.
 */
public class NotifyAlarmService extends Service {

    @Override
    public void onCreate() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        Logger.toast(getApplicationContext(),getString(R.string.alarm_text));
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

}
