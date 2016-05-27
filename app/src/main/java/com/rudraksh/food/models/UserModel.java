package com.rudraksh.food.models;


import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dell8 on 19/5/16.
 */
public class UserModel {

    @SerializedName("name")
    private String name;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("address")
    private String address;

    @SerializedName("pincode")
    private String pincode;

    @SerializedName("extra_food")
    private JSONObject extraFoodJsonObject;

    @SerializedName("total_amount")
    private int total_amount;

    @SerializedName("product_id")
    private int product_id;


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }


    private String address2;
    private int count;
    private int extra_food_id;

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public JSONObject getExtraFoodJsonObject() {
        return extraFoodJsonObject;
    }

    public void setExtraFoodJsonObject(JSONObject extraFoodJsonObject) {
        this.extraFoodJsonObject = extraFoodJsonObject;
    }

    public int getExtra_food_id() {
        return extra_food_id;
    }

    public void setExtra_food_id(int extra_food_id) {
        this.extra_food_id = extra_food_id;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


}
