package com.rudraksh.food.webservices;

import com.rudraksh.food.models.ExtraFoodModel;
import com.rudraksh.food.models.OfferResponseModel;
import com.rudraksh.food.models.ProductListModel;
import com.rudraksh.food.models.UserModel;
import com.rudraksh.food.models.UserResponseModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by dell8 on 19/5/16.
 */
public interface ApiInterface {
    @GET("products")
    Call<ProductListModel> getProductData();

    @GET("extraproducts")
    Call<ExtraFoodModel> getExtraFood();

    @POST("users")
    Call<UserResponseModel> signUp(@Body UserModel userModel);

    @GET("offers")
    Call<OfferResponseModel> getOfferData();

}
