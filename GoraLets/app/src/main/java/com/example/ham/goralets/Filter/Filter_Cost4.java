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
import com.example.ham.goralets.Deals.DealsAdapter;
import com.example.ham.goralets.Deals.DealsGetSet;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.Messages.Messages;
import com.example.ham.goralets.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Filter_Cost4 extends AppCompatActivity {

    private static final String URL_DEALS = "http://magreport.myapc.edu.ph/TaraPinas/Api_sortByCost4%20(1).php";
    List<DealsGetSet> C4List;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter__cost4);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.homeItem){
                    Intent i = new Intent(Filter_Cost4.this, MainActivity.class);
                    //i.putExtra("Email", str);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.bookingItem){
                    Intent i = new Intent(Filter_Cost4.this, Bookings.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.messagesItem){
                    Intent i = new Intent(Filter_Cost4.this, Messages.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.accountItem){
                    Intent i = new Intent(Filter_Cost4.this, Account2.class);
                    startActivity(i);
                }

                return false;
            }
        });
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        C4List = new ArrayList<>();
        loadDeals();

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

                                String agency = deals.getString("agency_name");

                                DealsGetSet dealsGetSet = new DealsGetSet(deal_id, deal_img1, deal_img2, deal_img3, deal_location, deal_startdate, deal_enddate, deal_price, deal_rating, deal_inclusions, deal_exclusions, agency);
                                C4List.add(dealsGetSet);
                            }
                            DealsAdapter adapter = new DealsAdapter(Filter_Cost4.this, C4List);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Filter_Cost4.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
