package com.rudraksh.food.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.rudraksh.food.utils.Constant;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by dell8 on 19/5/16.
 */
public class RestClient {
    private static RestClient instance;
    private ApiInterface apiInterface;

    public RestClient() {
        instance = this;
        final Gson gson = new GsonBuilder().registerTypeAdapterFactory( new ItemTypeAdapterFactory()).setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create();
        final OkHttpClient okHttpClient = new OkHttpClient();
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(interceptor);
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder().header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8").build();
                return chain.proceed(request);
            }
        });
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static RestClient getInstance() {
        return instance;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }
}
