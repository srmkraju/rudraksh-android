package com.rudraksh.food.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dell8 on 20/5/16.
 */
public class ProductListModel {
    @SerializedName("response")
    private boolean isresponse;

    @SerializedName("data")
    private ArrayList<ProductResponseData> data = new ArrayList<ProductResponseData>();
    public boolean isresponse() {
        return isresponse;
    }

    public void setIsresponse(boolean isresponse) {
        this.isresponse = isresponse;
    }

    public ArrayList<ProductResponseData> getData() {
        return data;
    }

    public void setData(ArrayList<ProductResponseData> data) {
        this.data = data;
    }



    public class ProductResponseData{
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("image")
        private String image;

        @SerializedName("amount")
        private int amount;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


    }

}
