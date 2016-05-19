package com.rudraksh.food.webservices;

import com.rudraksh.food.models.UserModel;
import com.rudraksh.food.models.UserResponseModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by dell8 on 19/5/16.
 */
public interface ApiInterface {
    @POST("rudraksh/public/users")
    Call<UserResponseModel> signUp(@Body UserModel userModel);

}
