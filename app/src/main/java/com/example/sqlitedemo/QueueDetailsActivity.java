package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                Intent intent=new Intent(QueueDetailsActivity.this,UserFuelfillingStatus.class);
                startActivity(intent);
            }
        });
    }
}