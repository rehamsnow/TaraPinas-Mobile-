package com.example.ham.goralets.Deals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ham.goralets.Account;
import com.example.ham.goralets.BookingStatus.BookingStatus;
import com.example.ham.goralets.Booking.Bookings;
import com.example.ham.goralets.BottomNavigationViewHelper;
import com.example.ham.goralets.Feedback.FeedbackAdapter;
import com.example.ham.goralets.Feedback.FeedbackGetSet;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.Messages.MessageCat;
import com.example.ham.goralets.Messages.Messages;
import com.example.ham.goralets.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ham on 3/8/2018.
 */

public class DealLayout extends AppCompatActivity {

    private static final String TAG = "deal_layout";
    private BottomNavigationView bottomNavigationView;
    private Button BtnBook;

    List<FeedbackGetSet> dealList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_layout);
        Log.d(TAG, "onCreate started");

        getIncomingIntent();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.homeItem){
                    Intent i = new Intent(DealLayout.this, MainActivity.class);
                    //i.putExtra("Email", str);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.bookingItem){
                    Intent i = new Intent(DealLayout.this, BookingStatus.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.messagesItem){
                    Intent i = new Intent(DealLayout.this, MessageCat.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.accountItem){
                    Intent i = new Intent(DealLayout.this, Account.class);
                    startActivity(i);
                }

                return false;
            }
        });


        BtnBook = (Button) findViewById(R.id.BtnBook);
        BtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DealPrice = getIntent().getStringExtra("DealPrice");
                String DealTitle = getIntent().getStringExtra("DealTitle");
                String DealStartdate = getIntent().getStringExtra("DealStartdate");
                String DealEnddate = getIntent().getStringExtra("DealEnddate");

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DealLayout.this);
                String ID = prefs.getString("ID", "no id"); //no id: default value
                String Fname = prefs.getString("Fname", "no fname");
                String Lname = prefs.getString("Lname", "no lname");

                Log.d(TAG, "DealLayout ID: " + ID);
                Log.d(TAG, "DealLayout Fname: " + Fname);
                Log.d(TAG, "DealLayout Lname: " + Lname);

                Intent i = new Intent(DealLayout.this, Bookings.class);

                startActivity(i);
            }
        });


        recyclerView =findViewById(R.id.DealRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setHasFixedSize(true);

        dealList = new ArrayList<>();
        loadFeedback();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");
        if (getIntent().hasExtra("DealImage") && getIntent().hasExtra("DealTitle") && getIntent().hasExtra("DealPrice")){
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String DealImage = getIntent().getStringExtra("DealImage");
            String DealImage2 = getIntent().getStringExtra("DealImage2");
            String DealImage3 = getIntent().getStringExtra("DealImage3");

            String DealTitle = getIntent().getStringExtra("DealTitle");
            String DealStartdate = getIntent().getStringExtra("DealStartdate");
            String DealEnddate = getIntent().getStringExtra("DealEnddate");

            String DealPrice = getIntent().getStringExtra("DealPrice");

            String DealRating = getIntent().getStringExtra("DealRating");
            String DealInclusions = getIntent().getStringExtra("DealInclusions");
            String DealExclusions = getIntent().getStringExtra("DealExclusions");

            String agency = getIntent().getStringExtra("agency");

            setImage(DealImage, DealImage2, DealImage3, DealTitle, DealStartdate, DealEnddate, DealPrice, DealRating, DealInclusions, DealExclusions, agency);
        }
    }

    private void setImage(String DealImage, String DealImage2, String DealImage3, String DealTitle, String DealStartdate, String DealEnddate, String DealPrice, String DealRating, String DealInclusions, String DealExclusions, String agency){
        Log.d(TAG, "setImage : setting images");

        ImageView img = findViewById(R.id.DealImage);
        Glide.with(this)
                .asBitmap()
                .load(DealImage)
                .into(img);

        ImageView img2 = findViewById(R.id.DealImage2);
        Glide.with(this)
                .asBitmap()
                .load(DealImage2)
                .into(img2);

        ImageView img3 = findViewById(R.id.DealImage3);
        Glide.with(this)
                .asBitmap()
                .load(DealImage3)
                .into(img3);

        TextView title = findViewById(R.id.DealTitle);
        title.setText(DealTitle);

        TextView startdate = findViewById(R.id.DealStartdate);
        startdate.setText(DealStartdate);

        TextView enddate = findViewById(R.id.DealEnddate);
        enddate.setText(DealEnddate);

        TextView price = findViewById(R.id.DealPrice);
        price.setText(DealPrice);

        TextView inclusions = findViewById(R.id.DealInclusions);
        inclusions.setText(DealInclusions);

        TextView exclusions = findViewById(R.id.DealExclusions);
        exclusions.setText(DealExclusions);

        TextView Agency = findViewById(R.id.DealTravelAgency);
        Agency.setText(agency);

    }

    private void loadFeedback(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(DealLayout.this);
        String DEALID = prefs.getString("DealID", "no id"); //no id: default value

        final String URL_DEALS = "http://magreport.myapc.edu.ph/TaraPinas/API_Dealfeedback.php?deal_id="+DEALID;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DEALS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject deals = array.getJSONObject(i);

                                String fname = deals.getString("fname");
                                Integer feedbackrating = deals.getInt("feedrating");
                                String lname = deals.getString("lname");
                                String comment = deals.getString("comment");
                                String feedbackimg = deals.getString("feedimg");

                                FeedbackGetSet feedbackGetSet = new FeedbackGetSet(fname, lname, feedbackrating, comment, feedbackimg);
                                dealList.add(feedbackGetSet);
                            }
                            FeedbackAdapter adapter = new FeedbackAdapter(DealLayout.this, dealList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DealLayout.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
