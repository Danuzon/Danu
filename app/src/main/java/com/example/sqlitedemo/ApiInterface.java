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

    /*
This class used for retrofit interface For All API method
 */

    //connecting app to backend using URL

    String JSON_URL_BASE = "http://192.168.8.100:45456";
    String JSON_URL_froPetrolStation = JSON_URL_BASE + "/api/petrolshed";

    String JSON_URL_froCountUser = JSON_URL_BASE + "/api/user/count/";
    String JSON_URL_forQtyPetrol = JSON_URL_BASE + "/api/petrolshed/petrolQty/";
    String JSON_URL_detailsWithChangedValue = JSON_URL_BASE + "/api/petrolshed/petrolShedDetails/";

    @POST("/api/petrolshed")
    Call<PetrolShedAdmin> createTask(@Body PetrolShedAdmin petrolShedAdmin);

    @POST("/api/user")
    Call<User> addUser(@Body User user);

    @PUT("/api/petrolshed")
    Call<PetrolShedAdmin> updatePetrolShed(@Body PetrolShedAdmin petrolShedAdmin);

    @DELETE("/api/user/{name}")
    Call<Void> deleteUser(@Path("name") String name);

    @PUT("/api/user")
    Call<User> updateUser(@Body User user);

}
