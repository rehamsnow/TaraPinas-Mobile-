package com.example.ham.goralets.TravelAgency;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.Rating;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import com.example.ham.goralets.BottomNavigationViewHelper;
import com.example.ham.goralets.Deals.DealLayout;
import com.example.ham.goralets.Deals.DealsAdapter;
import com.example.ham.goralets.Deals.DealsGetSet;
import com.example.ham.goralets.Feedback.FeedbackAdapter;
import com.example.ham.goralets.Feedback.FeedbackGetSet;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.Messages.MessageCat;
import com.example.ham.goralets.R;
import com.example.ham.goralets.Registration;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgencyAcct extends AppCompatActivity {

    private static final String TAG = "deal_layout";

    List<AgencyGetSet> dealList;
    RecyclerView recyclerView;

    List<AgencyDealGetSet> agencyList;
    RecyclerView agencyrecyclerView;

    TextView AgencyName, AgencyInfo, AgencyContact, AgencyReview;
    RatingBar AgencyRating;
    ImageView AgencyImage;

    RatingBar RARating;
    EditText RAFeedback;
    Button RABtnOK, BtnReviewAgency;

    String ServerURL = "http://magreport.myapc.edu.ph/TaraPinas/API_AddFeedbackAgency.php";

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_acct);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.homeItem){
                    Intent i = new Intent(AgencyAcct.this, MainActivity.class);
                    //i.putExtra("Email", str);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.bookingItem){
                    Intent i = new Intent(AgencyAcct.this, BookingStatus.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.messagesItem){
                    Intent i = new Intent(AgencyAcct.this, MessageCat.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.accountItem){
                    Intent i = new Intent(AgencyAcct.this, Account.class);
                    startActivity(i);
                }

                return false;
            }
        });


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String name = prefs.getString("A_Name", "no name"); //no id: default value
        String info = prefs.getString("A_Info", "no info");
        String contact = prefs.getString("A_Contact", "no contact");
        String img = prefs.getString("A_Img", "no img");
        String review = prefs.getString("A_Review", "no review");
        String stars = prefs.getString("A_String", "0");

        AgencyImage = (ImageView) findViewById(R.id.AgencyImg);
        Glide.with(AgencyAcct.this)
                .load(img)
                .into(AgencyImage);

        AgencyName = (TextView) findViewById(R.id.AgencyName);
        AgencyName.setText(name);

        AgencyInfo = (TextView) findViewById(R.id.AgencyInfo);
        AgencyInfo.setText(info);

        AgencyContact = (TextView) findViewById(R.id.AgencyContact);
        AgencyContact.setText(contact);

        AgencyReview = (TextView) findViewById(R.id.AgencyReview);
        AgencyReview.setText(review);

        AgencyRating = (RatingBar) findViewById(R.id.AgencyRatingBar);
        int stars3 = Integer.parseInt(String.valueOf(stars));
        AgencyRating.setRating(stars3);


        recyclerView =findViewById(R.id.RVAgencyFeedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setHasFixedSize(true);

        agencyrecyclerView =findViewById(R.id.RVAgencyDeal);
        agencyrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        agencyrecyclerView.setHasFixedSize(true);

        agencyList = new ArrayList<>();
        loadDeals();

        dealList = new ArrayList<>();
        loadFeedback();

        RARating = (RatingBar) findViewById(R.id.RARating);
        RAFeedback = (EditText) findViewById(R.id.RAFeedback);
        final String Feedback = RAFeedback.getText().toString();

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(AgencyAcct.this);
        final String ID = prefs2.getString("ID", "no id");

        RARating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final String Rating = String.valueOf(rating);
                String raating = Rating.toString();
            }
        });

            RABtnOK = (Button)findViewById(R.id.RABtnOK);
            RABtnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InsertData(Feedback, Feedback, ID,name);
                    Toast.makeText(AgencyAcct.this, "Feedback Sent!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void loadFeedback(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("A_Name", "no name");

        final String URL_DEALS = "http://magreport.myapc.edu.ph/TaraPinas/API_Agencyfeedback.php?agency_name="+name;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DEALS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject deals = array.getJSONObject(i);

                                String fname = deals.getString("user_fname");
                                Integer feedbackrating = deals.getInt("fagency_rating");
                                String lname = deals.getString("user_lname");
                                String comment = deals.getString("fagency_message");


                                AgencyGetSet feedbackGetSet = new AgencyGetSet(fname, lname, feedbackrating, comment);
                                dealList.add(feedbackGetSet);
                            }
                            AgencyAdapter adapter = new AgencyAdapter(AgencyAcct.this, dealList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AgencyAcct.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void loadDeals(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("A_Name", "no name");

        final String URL_DEALS = "http://magreport.myapc.edu.ph/TaraPinas/API_ViewAgencyDeals.php?agency_name="+name;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DEALS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject deals = array.getJSONObject(i);

                                String deal_desc = deals.getString("deal_desc");

                                String deal_img1 = deals.getString("deal_img1");
                                String deal_img2 = deals.getString("deal_img2");
                                String deal_img3 = deals.getString("deal_img3");

                                String deal_location = deals.getString("deal_location");
                                String deal_startdate = deals.getString("deal_startdate");
                                String deal_enddate = deals.getString("deal_enddate");

                                Double deal_price = deals.getDouble("deal_price");

                                String deal_inclusions = deals.getString("deal_inclusions");
                                String deal_exclusions = deals.getString("deal_exclusions");

                                AgencyDealGetSet dealsGetSet = new AgencyDealGetSet(deal_desc, deal_img1, deal_img2, deal_img3, deal_location, deal_startdate, deal_enddate, deal_price, deal_inclusions, deal_exclusions);
                                agencyList.add(dealsGetSet);
                            }

                            AgencyDealAdapter adapter = new AgencyDealAdapter(AgencyAcct.this, agencyList);
                            agencyrecyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AgencyAcct.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }



    public void InsertData(final String rating,
                           final String message,
                           final String user,
                           final String agency){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                String ratingHolder = rating;
                String messageHolder = message;
                String userHolder = user;
                String agencyHolder = agency;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("fagency_rating", ratingHolder));
                nameValuePairs.add(new BasicNameValuePair("fagency_message", messageHolder));
                nameValuePairs.add(new BasicNameValuePair("user_id", userHolder));
                nameValuePairs.add(new BasicNameValuePair("agency_id", agencyHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(ServerURL);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    org.apache.http.HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();

                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }
                return "Data Inserted Successfully";
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(AgencyAcct.this, "Data Submitted Successfully", Toast.LENGTH_LONG).show();

            }

        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(rating, message, user, agency);

    }
}
