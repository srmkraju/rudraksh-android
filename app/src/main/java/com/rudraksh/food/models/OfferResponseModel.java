package com.rudraksh.food.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dell8 on 20/5/16.
 */
public class OfferResponseModel {
    public boolean isresponse() {
        return isresponse;
    }

    public void setIsresponse(boolean isresponse) {
        this.isresponse = isresponse;
    }

    @SerializedName("response")
    private boolean isresponse;

    @SerializedName("data")
    private ArrayList<OfferData> data = new ArrayList<OfferData>();



    public ArrayList<OfferData> getData() {
        return data;
    }

    public void setData(ArrayList<OfferData> data) {
        this.data = data;
    }


    public class OfferData{
        @SerializedName("id")
        private int id;

        @SerializedName("text")
        private String text;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }


    }
}
