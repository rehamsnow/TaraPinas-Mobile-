package com.example.ham.goralets.Filter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ham.goralets.Deals.DealsAdapter;
import com.example.ham.goralets.Deals.DealsGetSet;
import com.example.ham.goralets.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Filter extends AppCompatActivity implements View.OnClickListener{

    Button BtnUnsorted,
            BtnByLocation,
            BtnByPrice,
            BtnByReview;
    ProgressDialog progressDialog;
    TextView textView;
    SharedPreferences sharedPreferences;
    String URL = "https://bustap.myapc.edu.ph/getData.php";
    String mn;
    DealsAdapter dealsAdapter;
    ArrayList<DealsGetSet> dealList= new ArrayList<DealsGetSet>();
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        progressDialog = new ProgressDialog(this);
        BtnUnsorted = (Button) findViewById(R.id.BtnUnsorted);
        BtnByLocation = (Button) findViewById(R.id.BtnByLocation);
        BtnByPrice = (Button) findViewById(R.id.BtnByPrice);
        BtnByReview = (Button) findViewById(R.id.BtnByReview);

        BtnUnsorted.setOnClickListener(this);
        BtnByLocation.setOnClickListener(this);
        BtnByPrice.setOnClickListener(this);
        BtnByReview.setOnClickListener(this);
        getDeals();
        sharedPreferences = getSharedPreferences("TFEmail", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        if(v==BtnUnsorted){
       //     startActivity(new Intent(this,Unsorted_deals.class));
        }
        if(v==BtnByLocation){

       //     startActivity(new Intent(this,filtered_location.class));
        }
        if(v==BtnByPrice){

         //   startActivity(new Intent(this,filtered_price.class));
        }
        if(v==BtnByReview){

         //   startActivity(new Intent(this,filter_review.class));
        }
    }

    public void getDeals(){
        progressDialog.setMessage("Fetching data from the Server...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        Toast.makeText(Filter.this, "Data Successfully Fetched", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject js = new JSONObject(response);

                            JSONArray jsonArray = js.getJSONArray("deals");

                            jsonString = jsonArray.toString();


                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("jsonString",jsonString);
                            editor.apply();

                            JSONArray sortedJsonArray = new JSONArray();
                            List<JSONObject> jsonValues = new ArrayList<JSONObject>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonValues.add(jsonArray.getJSONObject(i));
                            }
                            Collections.sort( jsonValues, new Comparator<JSONObject>() {
                                //You can change "Name" with "ID" if you want to sort by ID
                                private static final String deal_location = "deal_location";

                                @Override
                                public int compare(JSONObject a, JSONObject b) {
                                    String valA = new String();
                                    String valB = new String();

                                    try {
                                        valA = (String) a.get(deal_location);
                                        valB = (String) b.get(deal_location);
                                    }
                                    catch (JSONException e) {
                                        //do something
                                    }

                                    return valA.compareTo(valB);
                                }
                            });

                            for (int i = 0; i < jsonArray.length(); i++) {
                                sortedJsonArray.put(jsonValues.get(i));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);

    }
}
