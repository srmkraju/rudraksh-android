package com.rudraksh.food.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dell8 on 19/5/16.
 */
public class UserResponseModel {
    public boolean isresponse() {
        return isresponse;
    }

    public void setIsresponse(boolean isresponse) {
        this.isresponse = isresponse;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("response")
    private boolean isresponse;




    @SerializedName("message")
    private String message;

}
