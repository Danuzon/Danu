package com.example.sqlitedemo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin extends AppCompatActivity {
/*
This class used to implement the UI connection of the Admin page of Button and other petrol quantity input page
 */
    private Button btnSendPostRequest;
    private EditText petrolQtyInput;

// creating onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnSendPostRequest = findViewById(R.id.btnSendPostRequest);
        petrolQtyInput = findViewById(R.id.petrolQty);
        btnSendPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSendPostRequestClicked();
                Toast.makeText(Admin.this, "Quantity updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    // defining petrolshed admin
    private void btnSendPostRequestClicked() {

        String qty = petrolQtyInput.getText().toString();
        String petrolsShedName = LoginActivity.userNameFromLogin;
        PetrolShedAdmin petrolShedAdmin = new PetrolShedAdmin(petrolsShedName,"ABC23",qty);
        Log.e(TAG, "onResponseff: "+ petrolShedAdmin);
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<PetrolShedAdmin> call  = apiInterface.updatePetrolShed(petrolShedAdmin);
        call.enqueue((new Callback<PetrolShedAdmin>() {
            @Override
            public void onResponse(Call<PetrolShedAdmin> call, Response<PetrolShedAdmin> response) {

                Log.e(TAG, "onResponse: "+response.code());

            }

            @Override
            public void onFailure(Call<PetrolShedAdmin> call, Throwable t) {


            }
        }));
    }



}