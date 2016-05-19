package com.rudraksh.food.webservices;

/**
 * Created by dell8 on 19/5/16.
 */
public class RetrofitModel {
    private boolean success;
    private String message;
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}