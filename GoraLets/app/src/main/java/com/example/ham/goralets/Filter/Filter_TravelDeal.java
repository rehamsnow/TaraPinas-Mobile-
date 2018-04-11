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

public class Filter_TravelDeal extends AppCompatActivity {

    private static final String URL_DEALS = "http://bustap.myapc.edu.ph/Api_viewRecommendedTD.php";
    List<Filter_TravelDealGetSet> TDList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter__travel_deal);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.homeItem){
                    Intent i = new Intent(Filter_TravelDeal.this, MainActivity.class);
                    //i.putExtra("Email", str);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.bookingItem){
                    Intent i = new Intent(Filter_TravelDeal.this, Bookings.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.messagesItem){
                    Intent i = new Intent(Filter_TravelDeal.this, Messages.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.accountItem){
                    Intent i = new Intent(Filter_TravelDeal.this, Account2.class);
                    startActivity(i);
                }

                return false;
            }
        });
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TDList = new ArrayList<>();
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

                                String deal_img1 = deals.getString("deal_img1");
                                String deal_location = deals.getString("deal_location");
                                Double deal_price = deals.getDouble("deal_price");
                                Integer deal_review = deals.getInt("deal_review");

                                Filter_TravelDealGetSet filter_travelDealGetSet = new Filter_TravelDealGetSet(deal_img1, deal_location, deal_price, deal_review);
                                TDList.add(filter_travelDealGetSet);
                            }
                            Filter_TravelDealAdapter adapter = new Filter_TravelDealAdapter(Filter_TravelDeal.this, TDList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Filter_TravelDeal.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }



}
