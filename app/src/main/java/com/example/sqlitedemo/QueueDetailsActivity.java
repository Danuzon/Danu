package com.example.sqlitedemo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueueDetailsActivity extends AppCompatActivity {
    String name, age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView nameOut, ageOut;
        Button joButton;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);

        nameOut = findViewById(R.id.petrolShedName);
        ageOut = findViewById(R.id.petrolQuantity);
        joButton = findViewById(R.id.jButton);

        Intent intent = this.getIntent();
        if (intent != null){
             name = intent.getStringExtra("name");
             age = intent.getStringExtra("age");
        }

        nameOut.setText(name);
        ageOut.setText(age);


        joButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                btnSendPostRequestClicked();
                Intent intent=new Intent(QueueDetailsActivity.this,UserFuelfillingStatus.class);
                startActivity(intent);
            }
        });
    }

    private void btnSendPostRequestClicked() {

        final User user = new User("7","raddy","IT7","true","7");

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<User> call  = apiInterface.addUser(user);
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