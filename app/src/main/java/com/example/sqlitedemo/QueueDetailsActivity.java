package com.example.sqlitedemo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

public class QueueDetailsActivity extends AppCompatActivity {
    String PetrolShedName, AvailableQuantity, PetrolShedId;
    private TextView petrolShedNameOut, quantityOut, peopleInQueue;
    private Button joButton;
    String JSON_URL_FromApi = ApiInterface.JSON_URL_froCountUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_details);

        petrolShedNameOut = findViewById(R.id.petrolShedName);
        quantityOut = findViewById(R.id.petrolQuantity);
        Log.e(TAG, "onResponse Irfan: " + "testing");


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

        final User user = new User("7", "raddy", "IT7", "true", "7");

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<User> call = apiInterface.addUser(user);
        call.enqueue((new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.e(TAG, "onResponse: " + response.code());
//                Log.e(TAG, "onResponse: "+response.body(). getPetrolShedId());
//                Log.e(TAG, "onResponse: "+response.body().getPetrolShedName());
//                Log.e(TAG, "onResponse: "+response.body().getAvailableQuantity());
                //  Log.e(TAG, "onResponse: "+response.body().getId());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        }));
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
                    Log.d("success", "testfromUser method -3" + current);
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
//           String data3 = s.substring(1);
//           String data4 = data3.substring(0, data3.length() - 1);
//           String data5 = "[" + data4 + "]";
            Log.d("success FromUser", "test method77" + s);

            try {
//               Log.d("success", "test method6 data4" + data4);

                JSONArray jsonArray = new JSONArray(s);

                int numQry = jsonArray.length();
                peopleInQueue.setText(String.valueOf(numQry));
                Log.d("success", "test method7");
//               JSONArray jsonArray = jsonObject.getJSONArray("Friends");

                Log.d("success", "test method" + jsonArray);
                Log.d("success", "test method2" + s);
                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

//                    PetrolShedName = jsonObject1.getString("PetrolShedName");
//                    AvailableQuantity = jsonObject1.getString("AvailableQuantity");

                    //Hashmap
                    HashMap<String, String> friends = new HashMap<>();

//                    friends.put("PetrolShedName", PetrolShedName);
//                    friends.put("AvailableQuantity", AvailableQuantity);

//                    friendsList.add(friends);


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Displaying the results

//            ListAdapter adapter = new SimpleAdapter(
//                    HomeActivity.this,
//                    friendsList,
//                    R.layout.row_layout,
//                    new String[]{"PetrolShedName", "AvailableQuantity"},
//                    new int[]{R.id.textView, R.id.textView2});

//            lv.setAdapter(adapter);
//            lv.setClickable(true);
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Intent a = new Intent(HomeActivity.this, QueueDetailsActivity.class);
//                    a.putExtra("PetrolShedName", friendsList.get(i).get("PetrolShedName"));
//                    a.putExtra("AvailableQuantity", friendsList.get(i).get("AvailableQuantity"));
//                    startActivity(a);
//                }
//            });

        }
    }


}