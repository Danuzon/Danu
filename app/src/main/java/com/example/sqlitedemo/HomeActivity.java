package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {


    /*
After login from the user Home activity page will be display for the user
*/

    static String PetrolShedIdForOtherComponent;
    private ListView lv;

    String PetrolShedName, AvailableQuantity, PetrolShedId, noOfPerson;

    String JSON_URL = ApiInterface.JSON_URL_detailsWithChangedValue;

    ArrayList<HashMap<String, String>> friendsList;
    ArrayList<HashMap<String, String>> friendsList2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        friendsList = new ArrayList<>();
        friendsList2 = new ArrayList<>();
        lv = findViewById(R.id.listview);


        GetData getData = new GetData();
        getData.execute();


    }

// it is used to retrieve the Get method to see the all petrol station detail
    public class GetData extends AsyncTask<String, String, String> {

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

        // getting petrolshed details
        @Override
        protected void onPostExecute(String s) {

            String data = "[{\"name\": \"sandeep\",\"age\":30},{\"name\": \"vivan\",\"age\":5}]  ";


            try {

                JSONArray jsonArray = new JSONArray(s);



                Log.d("success", "test method" + jsonArray);

                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    PetrolShedName = jsonObject1.getString("PetrolShedName");
                    AvailableQuantity = jsonObject1.getString("AvailableQuantity");
                    PetrolShedId = jsonObject1.getString("PetrolShedId");

                    noOfPerson = jsonObject1.getString("peopleCount");

                    //Hashmap
                    HashMap<String, String> friends = new HashMap<>();

                    friends.put("PetrolShedName", PetrolShedName);
                    friends.put("AvailableQuantity", AvailableQuantity);
                    friends.put("PetrolShedId", PetrolShedId);
                    friends.put("noOfPerson", noOfPerson);

                    friendsList.add(friends);

                    HashMap<String, String> friends2 = new HashMap<>();

                    friends2.put("PetrolShedName", PetrolShedName);
                    friends2.put("AvailableQuantity", AvailableQuantity);
                    friends2.put("noOfPerson", noOfPerson);
                    friendsList2.add(friends);


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Displaying the results

            ListAdapter adapter = new SimpleAdapter(
                    HomeActivity.this,
                    friendsList,
                    R.layout.row_layout,
                    new String[]{"PetrolShedName", "AvailableQuantity", "noOfPerson"},
                    new int[]{R.id.textView, R.id.textView2, R.id.textView15});

            lv.setAdapter(adapter);
            lv.setClickable(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                //This place used to pass the variable from this page to other page

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    PetrolShedIdForOtherComponent = friendsList.get(i).get("PetrolShedId");

                    Intent a = new Intent(HomeActivity.this, QueueDetailsActivity.class);
                    a.putExtra("PetrolShedName", friendsList.get(i).get("PetrolShedName"));
                    a.putExtra("AvailableQuantity", friendsList.get(i).get("AvailableQuantity"));
                    a.putExtra("PetrolShedId", friendsList.get(i).get("PetrolShedId"));
                    startActivity(a);
                }
            });

        }
    }


}