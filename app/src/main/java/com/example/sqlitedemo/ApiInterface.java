package com.example.sqlitedemo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

    String JSON_URL_froPetrolStation = "http://192.168.1.192:45456/api/petrolshed";
    String JSON_URL_BASE = "http://192.168.1.14:45456";
    String JSON_URL_froCountUser = "http://192.168.1.192:45456/api/user/count/";

    @POST("/api/petrolshed")

    Call<PetrolShedAdmin> createTask(@Body PetrolShedAdmin petrolShedAdmin);

    @POST("/api/user")
    Call<User> addUser(@Body User user);

    @PUT("/api/petrolshed")

    Call<PetrolShedAdmin> updatePetrolShed(@Body PetrolShedAdmin petrolShedAdmin);

}
