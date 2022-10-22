package com.example.sqlitedemo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin extends AppCompatActivity {

    private Button btnSendPostRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnSendPostRequest = findViewById(R.id.btnSendPostRequest);
        btnSendPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSendPostRequestClicked();

            }
        });

    }
    private void btnSendPostRequestClicked() {

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<PetrolShedAdmin> call  = apiInterface.getPetrolShedInformation("121","ABCD");
        call.enqueue((new Callback<PetrolShedAdmin>() {
            @Override
            public void onResponse(Call<PetrolShedAdmin> call, Response<PetrolShedAdmin> response) {

                Log.e(TAG, "onResponse: "+response.code());
                Log.e(TAG, "onResponse: "+response.body(). getPetrolShedId());
                Log.e(TAG, "onResponse: "+response.body().getPetrolShedName());
                Log.e(TAG, "onResponse: "+response.body().getAvailableQuantity());
              //  Log.e(TAG, "onResponse: "+response.body().getId());


            }

            @Override
            public void onFailure(Call<PetrolShedAdmin> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getMessage() );

            }
        }));










    }



}