package com.example.ham.goralets.BookingStatus;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ham.goralets.R;
import com.example.ham.goralets.TravelAgency.AgencyAcct;

public class booking_status_details extends AppCompatActivity {

    TextView  BSID, BSLocation, BSStatus, BSPrice, BSFname, BSLname, BSContact, BSEmail, BSStart, BSEnd;
    ImageView BSImg1;
    Button BtnRateDeal;

    RatingBar RARating;
    EditText RAFeedback;
    Button RABtnOK, BtnReviewAgency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_status_details);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String book_id = prefs.getString("BS_id", "no id"); //no id: default value
        String deal_id = prefs.getString("BS_dealid", "dealid");
        String img = prefs.getString("BS_img", "no img");
        String location = prefs.getString("BS_location", "no img");
        String status = prefs.getString("BS_status", "no status");
        String price = prefs.getString("BS_price", "no price");
        String fname = prefs.getString("BS_fname", "no fname");
        String lname = prefs.getString("BS_lname", "no lname");
        String contact = prefs.getString("BS_contact", "no contact");
        String email = prefs.getString("BS_email", "no email");
        String start = prefs.getString("BS_start", "no start");
        String end = prefs.getString("BS_end", "no end");

        BSImg1 = (ImageView) findViewById(R.id.BSImg1);
        Glide.with(booking_status_details.this)
                .load(img)
                .into(BSImg1);

        BSStart = (TextView) findViewById(R.id.BSStartdate);
        BSStart.setText(start);

        BSEnd = (TextView) findViewById(R.id.BSEnddate);
        BSEnd.setText(end);

        BSFname =(TextView) findViewById(R.id.BSFirstName);
        BSFname.setText(fname);

        BSLname =(TextView) findViewById(R.id.BSLastName);
        BSLname.setText(lname);

        BSContact =(TextView) findViewById(R.id.BSContact);
        BSContact.setText(contact);

        BSEmail = (TextView) findViewById(R.id.BSEmail);
        BSEmail.setText(email);

        BSID = (TextView) findViewById(R.id.BSID);
        BSID.setText(book_id);

        BSLocation = (TextView) findViewById(R.id.BSLocation);
        BSLocation.setText(location);

        BSStatus = (TextView) findViewById(R.id.BSStatus);
        BSStatus.setText(status);

        BSPrice = (TextView) findViewById(R.id.BSPrice);
        BSPrice.setText(price);

        BtnRateDeal = (Button) findViewById(R.id.BtnRateDeal);
        BtnRateDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(booking_status_details.this);
                dialog.setContentView(R.layout.activity_rateagency);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                dialog.show();

                RARating = (RatingBar) findViewById(R.id.RARating);
                RAFeedback = (EditText) findViewById(R.id.RAFeedback);

                RARating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        String Rating = String.valueOf(rating);
                    }
                });

                RABtnOK = (Button)findViewById(R.id.RABtnOK);
                RABtnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(booking_status_details.this, "Feedback Sent!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });


    }
}
