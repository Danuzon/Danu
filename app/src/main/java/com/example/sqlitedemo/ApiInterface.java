package com.example.sqlitedemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/api/petrolshed")
    Call<PetrolShedAdmin> getPetrolShedInformation(@Field("PetrolShedId") String PetrolShedId, @Field("PetrolShedName") String PetrolShedName);
}
