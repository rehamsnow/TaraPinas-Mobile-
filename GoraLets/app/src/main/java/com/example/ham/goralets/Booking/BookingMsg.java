package com.example.ham.goralets.Booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.R;

public class BookingMsg extends AppCompatActivity {

    TextView BREmail, BRTravelAgency,BRBookingID;
    Button BRButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_msg);

        BREmail = (TextView) findViewById(R.id.BREmail);

        String email = getIntent().getStringExtra("cEmail");

        BREmail.setText(email);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String TA = prefs.getString("TravelAgency", "no TA"); //no id: default value
        String book_id = prefs.getString("BS_id", "no id"); //no id: default value

        BRTravelAgency=(TextView) findViewById(R.id.BRTravelAgency);
        BRTravelAgency.setText(TA);

        BRBookingID=(TextView) findViewById(R.id.BRBookingID);
        BRBookingID.setText(book_id);

        BRButton = (Button) findViewById(R.id.BRButton);
        BRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookingMsg.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
