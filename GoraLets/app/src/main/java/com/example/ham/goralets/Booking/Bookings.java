package com.example.ham.goralets.Booking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ham.goralets.Account;
import com.example.ham.goralets.BookingStatus.BookingStatus;
import com.example.ham.goralets.BottomNavigationViewHelper;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.Messages.Messages;
import com.example.ham.goralets.R;

import java.util.Calendar;

public class Bookings extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private static final String TAG = "Bookings";
    private TextView StartDate, SetPax;
    private TextView TDStartdate, TDEndate, OriginalPrice, PaxNum, BookingPrice, paxnum, duration;
    private Button BtnBook;
    private EditText days, pax1;
    private int finalValue, PN, FP;
    private int day2;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.homeItem) {
                    Intent i = new Intent(Bookings.this, MainActivity.class);
                    //i.putExtra("Email", str);
                    startActivity(i);
                } else if (item.getItemId() == R.id.bookingItem) {
                    Intent i = new Intent(Bookings.this, BookingStatus.class);
                    startActivity(i);
                } else if (item.getItemId() == R.id.messagesItem) {
                    Intent i = new Intent(Bookings.this, Messages.class);
                    startActivity(i);
                } else if (item.getItemId() == R.id.accountItem) {
                    Intent i = new Intent(Bookings.this, Account.class);
                    startActivity(i);
                }
                return false;
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String ID = prefs.getString("ID", "no id"); //no id: default value
        String Fname = prefs.getString("Fname", "no fname");
        String Lname = prefs.getString("Lname", "no lname");
        String DealPrice = prefs.getString("DEALPRICE", "0");


        Log.d(TAG, "Booking ID: " + ID);
        Log.d(TAG, "Booking Fname: " + Fname);
        Log.d(TAG, "Booking Lname: " + Lname);

            days = (EditText) findViewById(R.id.days);
            pax1 = (EditText) findViewById(R.id.pax);


 ////////////////////////////////////original price///////////////////////////
            OriginalPrice = (TextView) findViewById(R.id.OriginalPrice);
            SetPax = (TextView) findViewById(R.id.SetPax);

            OriginalPrice.setText(DealPrice);
            String FP1 = OriginalPrice.getText().toString();

            final Double FP = Double.parseDouble(FP1);


        SetPax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText pax = (EditText) findViewById(R.id.pax);
                    String paxstring = pax.getText().toString();

                    PaxNum = (TextView) findViewById(R.id.PaxNum);
                    PaxNum.setText(paxstring);

                    String Pax = PaxNum.getText().toString();

                    if (!Pax.isEmpty()) {
                        PN = Integer.parseInt(Pax);
                    }

                    Double FINAL = (FP*PN);
                    final String FINAL2 = String.valueOf(FINAL);
                    Log.d(TAG, "FINAL PRICE:"+FP+" x "+PN+ "="+FINAL2);

                    TextView price = findViewById(R.id.BookingPrice);
                    price.setText(FINAL2);

                    SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(Bookings.this);
                    SharedPreferences.Editor editor = prefs3.edit();

                    editor.putString("FINALPRICE", FINAL2);
                    editor.commit();
                }
            });


            TDStartdate = (TextView) findViewById(R.id.TDStartdate);
            TDEndate = (TextView) findViewById(R.id.TDEnddate);

            /*
            if (!Price.isEmpty()) {
                finalPrice = Integer.parseInt(Price);
                Log.d(TAG, "finalPrice : " + finalPrice);
            }
            */

        /*
        NumberPicker NumDays = (NumberPicker) findViewById(R.id.NumDays);
        NumDays.setMinValue(0);
        NumDays.setMaxValue(50);
        NumDays.setWrapSelectorWheel(true);

        NumDays.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                days.setText(newVal);
            }
        });
        */
            StartDate = (TextView) findViewById(R.id.StartDate);
            StartDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            Bookings.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateSetListener,
                            year, month, day);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, final int year, int month, final int day) {
                    month = month + 1;
                    Log.d(TAG, "onDateSet: date: " + month + "/" + day + "/" + year);

                    String date = month + "/" + day + "/" + year;
                    // StartDate.setText(date);
                    TDStartdate.setText(date);

                    final String x = days.getText().toString();

                    if (!x.isEmpty()) {
                        finalValue = Integer.parseInt(x);
                    }

                    if (day == 31) {
                        final int month2 = month + 1;

                        Log.d(TAG, "finalValue " + finalValue);
                        day2 = finalValue;
                        Log.d(TAG, "day2 is " + day2);

                        String enddate = month2 + "/" + day2 + "/" + year;
                        TDEndate.setText(enddate);

                    } else {
                        Log.d(TAG, "finalValue " + finalValue);
                        day2 = day + finalValue;
                        Log.d(TAG, "day2 is " + day2);

                        String enddate = month + "/" + day2 + "/" + year;
                        TDEndate.setText(enddate);
                    }
                }
            };

            BtnBook = (Button) findViewById(R.id.BtnBook);
            BtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                paxnum = (TextView) findViewById(R.id.PaxNum);
                String pax2 = paxnum.getText().toString();

                duration = (TextView) findViewById(R.id.days);
                String dur = duration.getText().toString();

                TextView sd = (TextView) findViewById(R.id.TDStartdate);
                String sd1 = sd.getText().toString();
                TextView ed = (TextView) findViewById(R.id.TDEnddate);
                String ed1 = ed.getText().toString();

                if (!dur.isEmpty() && !pax2.isEmpty()) {

                    Log.d(TAG, "Duration: " + duration);
                    String Title = getIntent().getStringExtra("Title");

                    Intent i = new Intent(Bookings.this, BookingContact.class);
                    //i.putExtra("Price", DealPrice);'
                    i.putExtra("Duration", dur);
                    i.putExtra("Pax", pax2);
                    i.putExtra("Title", Title);
                    i.putExtra("Startdate", sd1);
                    i.putExtra("Enddate", ed1);

                    startActivity(i);
                }
                else{
                    Toast.makeText(Bookings.this, "Please enter booking details.", Toast.LENGTH_SHORT).show();
                }

                if(dur.isEmpty()){
                    Toast.makeText(Bookings.this, "Please enter number of days.", Toast.LENGTH_SHORT).show();
                }
                if(pax2.isEmpty()){
                    Toast.makeText(Bookings.this, "Please enter number of travelers.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
