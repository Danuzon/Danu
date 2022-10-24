package com.example.sqlitedemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.8.101:45458")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }



}
