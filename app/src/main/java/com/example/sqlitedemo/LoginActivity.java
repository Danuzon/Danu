package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    /*
    * Login logic was implemented here if the person not in the database and password and user names are wrong,
    * this page is not allow to go further
    */

    EditText username,password;
    Button signin, regiter, jButton;
    DBHelper DB;

    static String userNameFromLogin;

    static boolean UserQueueStatus = true;
    static int carQ = 0;
    static int peopQ = 0;
    static int motoQ = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        signin=findViewById(R.id.signin1);
        regiter=findViewById(R.id.registerBtn);
        jButton=findViewById(R.id.jButton);
        DB=new DBHelper(this);


        regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jButton.setVisibility(view.VISIBLE);
                Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {

            //checking access to the admin


            @Override
            public void onClick(View v) {

                String user=username.getText().toString();
                String pass=password.getText().toString();

                userNameFromLogin = user;

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass=DB.checkusernamepassword(user,pass);

                    if(checkuserpass==true){
                        if(user.equals("adminAmal")){
                            Toast.makeText(LoginActivity.this, "your are admin person", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),Admin.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);}
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}