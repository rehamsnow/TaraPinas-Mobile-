package com.example.ham.goralets.Filter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ham.goralets.Account2;
import com.example.ham.goralets.Booking.Bookings;
import com.example.ham.goralets.BottomNavigationViewHelper;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.Messages.Messages;
import com.example.ham.goralets.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Filter_TravelAgency extends AppCompatActivity {

    private static final String URL_DEALS = "http://magreport.myapc.edu.ph/TaraPinas/API_ViewAgency.php";
    List<Filter_TravelAgencyGetSet> TAList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter__travel_agency);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.homeItem){
                    Intent i = new Intent(Filter_TravelAgency.this, MainActivity.class);
                    //i.putExtra("Email", str);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.bookingItem){
                    Intent i = new Intent(Filter_TravelAgency.this, Bookings.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.messagesItem){
                    Intent i = new Intent(Filter_TravelAgency.this, Messages.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.accountItem){
                    Intent i = new Intent(Filter_TravelAgency.this, Account2.class);
                    startActivity(i);
                }

                return false;
            }
        });
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TAList = new ArrayList<>();
        loadTravelAgency();
    }

    private void loadTravelAgency(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DEALS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject deals = array.getJSONObject(i);

                                String img = deals.getString("agency_img");
                                String name = deals.getString("agency_name");
                                String info = deals.getString("agency_info");
                                String contact = deals.getString("agency_contact");
                                Integer review = deals.getInt("fagency_id");
                                Integer stars = deals.getInt("fagency_rating");

                                Filter_TravelAgencyGetSet filter_travelAgencyGetSet = new Filter_TravelAgencyGetSet(img, name, info, contact, review, stars);
                                TAList.add(filter_travelAgencyGetSet);
                            }
                            Filter_TravelAgencyAdapter adapter = new Filter_TravelAgencyAdapter (Filter_TravelAgency.this, TAList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Filter_TravelAgency.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
