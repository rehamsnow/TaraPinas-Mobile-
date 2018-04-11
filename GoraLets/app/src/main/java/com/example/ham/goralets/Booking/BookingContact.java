package com.example.ham.goralets.Booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ham.goralets.R;

public class BookingContact extends AppCompatActivity {
    private static final String TAG = "Bookings Contact";
    EditText ContactFname, ContactLname, ContactEmail, ContactNum;
    Button BtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_contact);

        ContactFname = (EditText) findViewById(R.id.ContactFname);
        ContactLname = (EditText) findViewById(R.id.ContactLname);
        ContactEmail = (EditText) findViewById(R.id.ContactEmail);
        ContactNum = (EditText) findViewById(R.id.ContactNum);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String ID = prefs.getString("ID", "no id"); //no id: default value
        String Fname = prefs.getString("Fname", "no fname");
        String Lname = prefs.getString("Lname", "no lname");
        String Email = prefs.getString("Email", "no Email");
        String Contact = prefs.getString("Contact", "no Contact");

        Log.d(TAG, "Booking C ID: " + ID);
        Log.d(TAG, "Booking C Fname: " + Fname);
        Log.d(TAG, "Booking C Lname: " + Lname);
        Log.d(TAG, "Booking C Email: " + Email);
        Log.d(TAG, "Booking C Contact: " + Contact);

        ContactFname.setText(Fname);
        ContactLname.setText(Lname);
        ContactEmail.setText(Email);
        ContactNum.setText(Contact);

        final String fname = ContactFname.getText().toString();
        final String lname = ContactLname.getText().toString();
        final String email = ContactEmail.getText().toString();
        final String contact = ContactNum.getText().toString();

        final String Title = getIntent().getStringExtra("Title");
        final String Startdate = getIntent().getStringExtra("Startdate");
        final String Enddate = getIntent().getStringExtra("Enddate");
        final String Pax = getIntent().getStringExtra("Pax");
        final String Duration = getIntent().getStringExtra("Duration");

        Log.d(TAG, "Booking C START: " + Startdate);
        Log.d(TAG, "Booking C END: " + Enddate);
        Log.d(TAG, "Booking C PAX: " + Pax);



        BtnSubmit = (Button) findViewById(R.id.ContactSubmit);
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookingContact.this, BookingReview.class);
                i.putExtra("Title", Title);
                i.putExtra("Startdate", Startdate);
                i.putExtra("Enddate", Enddate);
                i.putExtra("Pax", Pax);
                i.putExtra("Duration", Duration);

                i.putExtra("Fname", fname);
                i.putExtra("Lname", lname);
                i.putExtra("Email", email);
                i.putExtra("Contact", contact);

                startActivity(i);
            }
        });



    }
}
