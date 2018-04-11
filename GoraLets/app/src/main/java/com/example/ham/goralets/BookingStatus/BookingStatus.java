package com.example.ham.goralets.BookingStatus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ham.goralets.Deals.DealsAdapter;
import com.example.ham.goralets.Deals.DealsGetSet;
import com.example.ham.goralets.Feedback.FeedbackGetSet;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookingStatus extends AppCompatActivity {

    TextView BSLocation, BSStartdate, BSEnddate, BSStatus;
    Button BtnViewBooking;

    List<BookingStatusGetSet> dealList;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_status_list);


        recyclerView =findViewById(R.id.bookingrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        dealList = new ArrayList<>();
       Booking();
    }

    private void Booking(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String Email = prefs.getString("Email", "no email"); //no id: default value

        final String URL_DEALS = "http://magreport.myapc.edu.ph/TaraPinas/API_ViewBook.php?user_email="+Email;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DEALS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject deals = array.getJSONObject(i);

                                Integer id = deals.getInt("book_id");

                                String location = deals.getString("deal_location");
                                String  img1 = deals.getString("deal_img1");

                                String status = deals.getString("book_status");
                                Double price = deals.getDouble("book_price");
                                String start = deals.getString("book_start");
                                String end = deals.getString("book_end");
                                Integer pax = deals.getInt("book_pax");
                                String fname = deals.getString("book_fname");
                                String lname = deals.getString("book_lname");
                                String email = deals.getString("book_email");
                                String contact = deals.getString("book_contact");

                                BookingStatusGetSet bookingStatusGetSet = new BookingStatusGetSet(id, location, img1, status, price, start, end, pax, fname, lname, email, contact);
                                dealList.add(bookingStatusGetSet);
                            }

                            BookingStatusAdapter adapter = new BookingStatusAdapter(BookingStatus.this, dealList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookingStatus.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
