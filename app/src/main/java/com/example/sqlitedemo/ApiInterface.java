package com.example.sqlitedemo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    String JSON_URL_froPetrolStation = "http://192.168.1.14:45456/api/petrolshed";
    String JSON_URL_BASE = "http://192.168.1.14:45456";
    String JSON_URL_froCountUser = "http://192.168.1.14:45456/api/user/count/";

    @POST("/api/petrolshed")

    Call<PetrolShedAdmin> createTask(@Body PetrolShedAdmin petrolShedAdmin);

    @POST("/api/user")
    Call<User> addUser(@Body User user);

    @PUT("/api/petrolshed")

    Call<PetrolShedAdmin> updatePetrolShed(@Body PetrolShedAdmin petrolShedAdmin);

    @DELETE("/api/user/{name}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
//    Call<PetrolShedAdmin> getPetrolShedInformation(@Field("PetrolShedId") String PetrolShedId, @Field("PetrolShedName") String PetrolShedName);
    Call<Void> deleteUser(@Path("name") String name);

    @PUT("/api/user")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
//    Call<PetrolShedAdmin> getPetrolShedInformation(@Field("PetrolShedId") String PetrolShedId, @Field("PetrolShedName") String PetrolShedName);
    Call<User> updateUser(@Body User user);

}
