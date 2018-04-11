package com.example.ham.goralets.Messages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.example.ham.goralets.Account;
import com.example.ham.goralets.BookingStatus.BookingStatus;
import com.example.ham.goralets.BottomNavigationViewHelper;
import com.example.ham.goralets.Deals.DealLayout;
import com.example.ham.goralets.Feedback.FeedbackAdapter;
import com.example.ham.goralets.Feedback.FeedbackGetSet;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Messages extends AppCompatActivity {


    List<MessagesGetSet> dealList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_recycler);

        recyclerView =findViewById(R.id.messagerecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setHasFixedSize(true);

        dealList = new ArrayList<>();
        loadFeedback();
    }
    private void loadFeedback(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String ID = prefs.getString("ID", "no id"); //no id: default value


        final String URL_DEALS = "http://magreport.myapc.edu.ph/TaraPinas/API_Inquiry.php?user_id="+ID+"&agency_id=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DEALS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject deals = array.getJSONObject(i);

                                String message = deals.getString("message");

                                MessagesGetSet feedbackGetSet = new MessagesGetSet(message);
                                dealList.add(feedbackGetSet);
                            }
                            MessagesAdapter adapter = new MessagesAdapter(Messages.this, dealList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Messages.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
