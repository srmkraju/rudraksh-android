package com.rudraksh.food;

import android.app.Application;

import com.rudraksh.food.webservices.RestClient;

/**
 * Created by Raju on 4/13/2016.
 */
public class RudrakshApplication extends Application {

    private static RudrakshApplication instance;

    public RudrakshApplication(){
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        new RestClient();

        
    }

    public static RudrakshApplication getInstance() {
        return instance;
    }

}
