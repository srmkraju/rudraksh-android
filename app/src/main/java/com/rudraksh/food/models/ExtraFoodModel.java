package com.rudraksh.food.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by dell8 on 20/5/16.
 */
public class ExtraFoodModel {
    @SerializedName("response")
    private boolean isResponse;

    @SerializedName("data")
    private ArrayList<ExtraFoodResponseModel> data = new ArrayList<ExtraFoodResponseModel>();

    public boolean isResponse() {
        return isResponse;
    }

    public void setResponse(boolean response) {
        isResponse = response;
    }

    public ArrayList<ExtraFoodResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<ExtraFoodResponseModel> data) {
        this.data = data;
    }



    public class ExtraFoodResponseModel implements Parcelable{
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String extra_food_name;

        @SerializedName("amount")
        private int amount;

        private int item_count=0;


        protected ExtraFoodResponseModel(Parcel in) {
            id = in.readInt();
            extra_food_name = in.readString();
            amount = in.readInt();
            item_count = in.readInt();
        }

        public final Creator<ExtraFoodResponseModel> CREATOR = new Creator<ExtraFoodResponseModel>() {
            @Override
            public ExtraFoodResponseModel createFromParcel(Parcel in) {
                return new ExtraFoodResponseModel(in);
            }

            @Override
            public ExtraFoodResponseModel[] newArray(int size) {
                return new ExtraFoodResponseModel[size];
            }
        };

        public int getItem_count() {
            return item_count;
        }

        public void setItem_count(int item_count) {
            this.item_count = item_count;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getExtra_food_name() {
            return extra_food_name;
        }

        public void setExtra_food_name(String extra_food_name) {
            this.extra_food_name = extra_food_name;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(extra_food_name);
            dest.writeInt(amount);
            dest.writeInt(item_count);
        }
    }
}
