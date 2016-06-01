package com.rudraksh.food.models;


import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

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

    @SerializedName("address2")
    private String address2;

    @SerializedName("pincode")
    private String pincode;


    @SerializedName("extra_products")
    private String extra_products;

    @SerializedName("amount")
    private int amount;

    @SerializedName("product_id")
    private int product_id;

    @SerializedName("product_count")
    private int product_count;

    @SerializedName("have_extra")
    private int have_extra;


    public int getHave_extra() {
        return have_extra;
    }

    public void setHave_extra(int have_extra) {
        this.have_extra = have_extra;
    }


    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public void setExtra_products(String extra_products) {
        this.extra_products = extra_products;
    }
}
