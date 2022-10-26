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
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueueDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String PetrolShedName, AvailableQuantity, PetrolShedId;
    private TextView petrolShedNameOut, quantityOut, peopleInQueue;
    private Button joButton;
    String JSON_URL_FromApi = ApiInterface.JSON_URL_froCountUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    petrolShedNameOut = findViewById(R.id.petrolShedName);
        quantityOut = findViewById(R.id.petrolQuantity);



        peopleInQueue = findViewById(R.id.numberOfPeople);
        joButton = findViewById(R.id.jButton);

        Intent intent = this.getIntent();
        if (intent != null) {
            PetrolShedName = intent.getStringExtra("PetrolShedName");
            AvailableQuantity = intent.getStringExtra("AvailableQuantity");
            PetrolShedId = intent.getStringExtra("PetrolShedId");
        }

        petrolShedNameOut.setText(PetrolShedName);
        quantityOut.setText(AvailableQuantity);


        joButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                btnSendPostRequestClicked();
                Intent intent = new Intent(QueueDetailsActivity.this, UserFuelfillingStatus.class);
                startActivity(intent);
            }
        });

        QueueDetailsActivity.GetData getData = new QueueDetailsActivity.GetData();
        getData.execute();
    }

    private void btnSendPostRequestClicked() {

        final User user = new User("7", "raddy", PetrolShedId, "join", "1");

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

        String JSON_URL = JSON_URL_FromApi + PetrolShedId;

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
                peopleInQueue.setText(String.valueOf(numQry));


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