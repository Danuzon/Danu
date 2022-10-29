package com.example.sqlitedemo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueueDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


       /*
The user once login he can see the available petrol station can see after that he can see the details of the petrol station
it is visible to the user that number of people and number of vehicle by category
 */


    String PetrolShedName, AvailableQuantity, PetrolShedId, PeopleInQueue, CarCount, MotoCount;
    private TextView petrolShedNameOut, quantityOut, peopleInQueue, carCount, motoCount;
    private Button joButton;
    private Spinner spinner;
    int carQ,peopQ,motoQ;
    boolean UserQueueStatus = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);

        // adding combo box
        spinner =(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        petrolShedNameOut = findViewById(R.id.petrolShedName);
        quantityOut = findViewById(R.id.petrolQuantity);
        carCount = findViewById(R.id.car);
        motoCount = findViewById(R.id.motorBicycle);
        peopleInQueue = findViewById(R.id.numberOfPeople);
        joButton = findViewById(R.id.jButton);

        Intent intent = this.getIntent();
        if (intent != null) {
            PetrolShedName = intent.getStringExtra("PetrolShedName");
            AvailableQuantity = intent.getStringExtra("AvailableQuantity");
            PetrolShedId = intent.getStringExtra("PetrolShedId");
            PeopleInQueue = intent.getStringExtra("noOfPerson");
            CarCount = intent.getStringExtra("carCount");
            MotoCount = intent.getStringExtra("motoBiCount");

        }

        carQ = Integer.parseInt(CarCount);
        peopQ = Integer.parseInt(PeopleInQueue);
        motoQ = Integer.parseInt(MotoCount);

        petrolShedNameOut.setText(PetrolShedName);
        quantityOut.setText(AvailableQuantity);


            peopleInQueue.setText(PeopleInQueue);
            carCount.setText(CarCount);
            motoCount.setText(MotoCount);

            peopleInQueue.setText(String.valueOf(peopQ));
            carCount.setText(String.valueOf(carQ));
            motoCount.setText(String.valueOf(motoQ));






//        joButton.setVisibility(View.INVISIBLE);

        joButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String vehiT = spinner.getSelectedItem().toString();

                if(vehiT.equals("null")){
                    Toast.makeText(QueueDetailsActivity.this, "please select the correct vehicle type", Toast.LENGTH_SHORT).show();
                }else{
                    if(UserQueueStatus){
                        if(vehiT.equals("car")){
                            carQ++;
                        }
                        if(vehiT.equals("motor bike")){
                            motoQ++;
                        }
                        peopQ++;
                        peopleInQueue.setText(String.valueOf(peopQ));
                        carCount.setText(String.valueOf(carQ));
                        motoCount.setText(String.valueOf(motoQ));
                        UserQueueStatus = false;
                        btnSendPostRequestClicked();
                        Toast.makeText(QueueDetailsActivity.this,"Jointed in the queue", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QueueDetailsActivity.this, UserFuelfillingStatus.class);
                        intent.putExtra("PetrolShedName",PetrolShedName);
                        startActivity(intent);
                    }else{
                        Toast.makeText(QueueDetailsActivity.this,"Already Jointed in the queue", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QueueDetailsActivity.this, UserFuelfillingStatus.class);
                        startActivity(intent);
                    }

                }

            }
        });

        QueueDetailsActivity.GetData getData = new QueueDetailsActivity.GetData();
        getData.execute();

    }

    //defining user requirements checking
    private void btnSendPostRequestClicked() {

        String UserName = LoginActivity.userNameFromLogin;
        String vehicleTypes = spinner.getSelectedItem().toString();
        final User user = new User(UserName, PetrolShedId,"join",vehicleTypes,"0");

        Log.e(TAG, "onResponseUser: " + user);

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<User> call = apiInterface.addUser(user);
        call.enqueue((new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.e(TAG, "onResponse: " + response.code());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {



            }
        }));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class GetData extends AsyncTask<String, String, String> {

        String JSON_URL = ApiInterface.JSON_URL_froCountUser + PetrolShedId;

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);

                    int data = isr.read();
                    while (data != -1) {

                        current += (char) data;
                        data = isr.read();

                    }

                    return current;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            String data = "[{\"name\": \"sandeep\",\"age\":30},{\"name\": \"vivan\",\"age\":5}]  ";

            try {

                JSONArray jsonArray = new JSONArray(s);

                int numQry = jsonArray.length();
//                peopleInQueue.setText(String.valueOf(numQry));


                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);


                    //Hashmap
                    HashMap<String, String> friends = new HashMap<>();


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }




        }
    }


}