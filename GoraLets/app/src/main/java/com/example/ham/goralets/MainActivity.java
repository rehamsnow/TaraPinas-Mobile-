package com.example.ham.goralets;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ham.goralets.BookingStatus.BookingStatus;
import com.example.ham.goralets.Deals.DealsAdapter;
import com.example.ham.goralets.Deals.DealsGetSet;
import com.example.ham.goralets.Filter.FilteredCheckbox;
import com.example.ham.goralets.Messages.MessageCat;
import com.example.ham.goralets.Messages.Messages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private TextView TVSample;
    private BottomNavigationView bottomNavigationView;
    private static final String TAG = "MainActivity";

    private static final String URL_DEALS = "http://magreport.myapc.edu.ph/TaraPinas/API_Deals.php";
    List<DealsGetSet> dealList;
    RecyclerView recyclerView;
    Button BtnFilter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        String ID = getIntent().getStringExtra("ID");
        String Fname = getIntent().getStringExtra("Fname");
        String Lname = getIntent().getStringExtra("Lname");
        String Email = getIntent().getStringExtra("Email");
        String Contact = getIntent().getStringExtra("Contact");
        String Birthdate = getIntent().getStringExtra("Birthdate");

        Log.d(TAG, "ID: " + ID);
        Log.d(TAG, "Fname: " + Fname);
        Log.d(TAG, "Lname: " + Lname);
        */

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String ID = prefs.getString("ID", "no id"); //no id: default value
        String Fname = prefs.getString("Fname", "no fname");
        String Lname = prefs.getString("Lname", "no lname");

        Log.d(TAG, "SP ID: " + ID);
        Log.d(TAG, "SP Fname: " + Fname);
        Log.d(TAG, "SP Lname: " + Lname);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.homeItem){
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.bookingItem){
                    Intent i = new Intent(MainActivity.this, BookingStatus.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.messagesItem){
                    Intent i = new Intent(MainActivity.this, MessageCat.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.accountItem){
                    Intent i = new Intent(MainActivity.this, Account2.class);
                    startActivity(i);
                }

                return false;
            }
        });

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        dealList = new ArrayList<>();
        loadDeals();

        BtnFilter = (Button)findViewById(R.id.BtnFilter1);
        BtnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FilteredCheckbox.class);
                startActivity(i);
            }
        });

    }

    private void loadDeals(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DEALS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject deals = array.getJSONObject(i);

                                Integer deal_id = deals.getInt("deal_id");

                                String deal_img1 = deals.getString("deal_img1");
                                String deal_img2 = deals.getString("deal_img2");
                                String deal_img3 = deals.getString("deal_img3");

                                String deal_location = deals.getString("deal_location");
                                String deal_startdate = deals.getString("deal_startdate");
                                String deal_enddate = deals.getString("deal_enddate");

                                Integer deal_rating = deals.getInt("deal_rating");
                                Double deal_price = deals.getDouble("deal_price");
                                String deal_inclusions = deals.getString("deal_inclusions");
                                String deal_exclusions = deals.getString("deal_exclusions");

                                String agency= deals.getString("agency_name");

                                DealsGetSet dealsGetSet = new DealsGetSet(deal_id, deal_img1, deal_img2, deal_img3, deal_location, deal_startdate, deal_enddate, deal_price, deal_rating, deal_inclusions, deal_exclusions, agency);
                                dealList.add(dealsGetSet);
                            }

                            DealsAdapter adapter = new DealsAdapter(MainActivity.this, dealList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }



}
