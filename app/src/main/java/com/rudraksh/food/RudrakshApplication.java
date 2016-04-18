package com.rudraksh.food;

import android.app.Application;

/**
 * Created by Raju on 4/13/2016.
 */
public class RudrakshApplication extends Application {

    private RudrakshApplication instance;

    public RudrakshApplication(){
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        
    }

    public RudrakshApplication getInstance() {
        return instance;
    }

}
