package com.example.sqlitedemo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface ApiInterface {

    String JSON_URL_froPetrolStation = "http://192.168.8.101:45458/api/petrolshed";
    String JSON_URL_BASE = "http://192.168.8.101:45458";

    @POST("/api/petrolshed")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
//    Call<PetrolShedAdmin> getPetrolShedInformation(@Field("PetrolShedId") String PetrolShedId, @Field("PetrolShedName") String PetrolShedName);
    Call<PetrolShedAdmin> createTask(@Body PetrolShedAdmin petrolShedAdmin);

    @POST("/api/user")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
//    Call<PetrolShedAdmin> getPetrolShedInformation(@Field("PetrolShedId") String PetrolShedId, @Field("PetrolShedName") String PetrolShedName);
    Call<User> addUser(@Body User user);
}
