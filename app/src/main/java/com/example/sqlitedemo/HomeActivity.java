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

    private ListView lv;

    String namey,age;

    private static String JSON_URL = "http://192.168.1.192:45455/api/petrolshed";
//private static String JSON_URL = "https://run.mocky.io/v3/76293af7-867f-407a-ae0a-b3da44337801";

//    private static String JSON_URL = "https://run.mocky.io/v3/e2819273-f812-416e-a589-399ba17ecaf4";
//     private static String JSON_URL = "https://run.mocky.io/v3/3f5709d9-7d7d-4ebe-ac1e-cec42a8882cb";


    ArrayList<HashMap<String,String>> friendsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        friendsList = new ArrayList<>();
        lv = findViewById(R.id.listview);



        GetData getData = new GetData();
        getData.execute();


    }







   public class GetData extends AsyncTask<String, String, String>{

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
                   Log.d("success", "test method -3" + current);
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
           }catch (Exception e) {

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
           Log.d("success", "test method66" + s);

           try {
//               Log.d("success", "test method6 data4" + data4);

               JSONArray jsonArray = new JSONArray(s);

               Log.d("success", "test method7");
//               JSONArray jsonArray = jsonObject.getJSONArray("Friends");

               Log.d("success", "test method" + jsonArray);
               Log.d("success", "test method2" + s);
               for (int i=0; i<jsonArray.length(); i++){


                   JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                   namey = jsonObject1.getString("PetrolShedName");
                   age = jsonObject1.getString("AvailableQuantity");



                   //Hashmap
                   HashMap<String, String> friends = new HashMap<>();

                   friends.put("name", namey);
                   friends.put("age", age);

                   friendsList.add(friends);






               }

           } catch (JSONException e) {
               e.printStackTrace();
           }

           //Displaying the results

           ListAdapter adapter = new SimpleAdapter(
                   HomeActivity.this,
                   friendsList,
                   R.layout.row_layout,
                   new String[] {"name", "age"},
                   new int[]{R.id.textView, R.id.textView2});

           lv.setAdapter(adapter);
           lv.setClickable(true);
           lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   Intent a = new Intent(HomeActivity.this,QueueDetailsActivity.class);
                   a.putExtra("name",friendsList.get(i).get("name"));
                   a.putExtra("age",friendsList.get(i).get("age"));
                   startActivity(a);
               }
           });

       }
   }












}