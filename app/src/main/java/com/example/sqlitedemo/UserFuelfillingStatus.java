package com.example.sqlitedemo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 User can be update the status
 if he or she petrol filled or not
 */
public class UserFuelfillingStatus extends AppCompatActivity {

    static boolean buttonState = true;

    public String PetrolShedName;
    Button exitBeforePumpFuel, exitAfterPumpFuel;
    EditText petrolQty;
    TextView petrolShedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_fuelfilling_status);

        exitAfterPumpFuel = findViewById(R.id.ExitAfterPumpFuel);
        exitBeforePumpFuel = findViewById(R.id.exitBeforePumpFuel);
        petrolQty = findViewById(R.id.petrolQtyFromUser);
        petrolShedName = findViewById(R.id.tViewPetrolShedName);

        Intent intent = this.getIntent();
        PetrolShedName = intent.getStringExtra("PetrolShedName");
        petrolShedName.setText(PetrolShedName);
// implement user exit before pump fuel method

        exitBeforePumpFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExitBeforePumpFuelClicked();
                Toast.makeText(UserFuelfillingStatus.this, "user have been removed from queue", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

// implement user exit after pump fuel method
        exitAfterPumpFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnExitAfterPumpFuelClicked();
                Toast.makeText(UserFuelfillingStatus.this, "completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    private void btnExitBeforePumpFuelClicked() {
        Log.e(TAG, "Testing of exitbefore pump: ");
        String nameOfUser = LoginActivity.userNameFromLogin ;
        Log.e(TAG, "onResponsenameOfUser: " + nameOfUser);
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Void> call = apiInterface.deleteUser(nameOfUser);
        call.enqueue((new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Log.e(TAG, "onResponse: " + response.code());
//                Log.e(TAG, "onResponse: "+response.body(). getPetrolShedId());
//                Log.e(TAG, "onResponse: "+response.body().getPetrolShedName());
//                Log.e(TAG, "onResponse: "+response.body().getAvailableQuantity());
                //  Log.e(TAG, "onResponse: "+response.body().getId());


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        }));
    }

    private void btnExitAfterPumpFuelClicked() {
        Log.e(TAG, "Testing of exitBefore pump: ");
        String nameOfUser = LoginActivity.userNameFromLogin ;
        String petrolShedString = HomeActivity.PetrolShedIdForOtherComponent;
        String petrolQtyString = petrolQty.getText().toString();


        User user = new User(nameOfUser,petrolShedString,"completed","car", petrolQtyString);
        Log.e(TAG, "onResponse: "+ user);
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<User> call  = apiInterface.updateUser(user);
        call.enqueue((new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.e(TAG, "onResponse: "+response.code());
//                Log.e(TAG, "onResponse: "+response.body(). getPetrolShedId());
//                Log.e(TAG, "onResponse: "+response.body().getPetrolShedName());
//                Log.e(TAG, "onResponse: "+response.body().getAvailableQuantity());
                //  Log.e(TAG, "onResponse: "+response.body().getId());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getMessage() );

            }
        }));
    }

}