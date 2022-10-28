package com.example.sqlitedemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 defining retrofit library connection to the Api for
 particular URL is used to connect the API
 */

public class RetrofitClient {
    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.JSON_URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }



}
