package com.example.ham.goralets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ham.goralets.BookingStatus.BookingStatus;
import com.example.ham.goralets.EditAccount.EditUser;
import com.example.ham.goralets.LoginF.Login;
import com.example.ham.goralets.Messages.MessageCat;
import com.example.ham.goralets.Messages.Messages;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ham on 3/1/2018.
 */

public class Account2 extends AppCompatActivity{

    public static TextView data;
    public static TextView TVFname;
    public static TextView TVLname;
    public static TextView TVContact;
    public static TextView TVBirthdate;
    public static TextView TVEmail;
    public static String Email;
    private static Context mCtx;

    Button BtnLogout;

    private FirebaseAuth auth;
    private static final String TAG = "Profile Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_account);

            auth = FirebaseAuth.getInstance();

            BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
            BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    if(item.getItemId() == R.id.homeItem){
                        Intent i = new Intent(Account2.this, MainActivity.class);
                        //i.putExtra("Email", str);
                        startActivity(i);
                    }
                    else if(item.getItemId() == R.id.bookingItem){
                        Intent i = new Intent(Account2.this, BookingStatus.class);
                        startActivity(i);
                    }
                    else if (item.getItemId() == R.id.messagesItem){
                        Intent i = new Intent(Account2.this, MessageCat.class);
                        startActivity(i);
                    }
                    else if(item.getItemId() == R.id.accountItem){
                        Intent i = new Intent(Account2.this, Account.class);
                        startActivity(i);
                    }
                    return false;
                }
            });

            //data = (TextView)findViewById(R.id.TVSample);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String ID = prefs.getString("ID", "no id"); //no id: default value
            String Fname = prefs.getString("Fname", "no fname");
            String Lname = prefs.getString("Lname", "no lname");
            String Email = prefs.getString("Email", "no Email");
            String Contact = prefs.getString("Contact", "no contact");
            String Birthdate = prefs.getString("Birthdate", "no bdate");

            Log.d(TAG, "Profile ID: " + ID);
            Log.d(TAG, "Profile Fname: " + Fname);
            Log.d(TAG, "Profile Lname: " + Lname);
            Log.d(TAG, "Profile Email: " + Email);
            Log.d(TAG, "Profile Contact: " + Contact);
            Log.d(TAG, "Profile Birthdate: " + Birthdate);

            //Email = getIntent().getStringExtra("Email");

            TVFname = (TextView)findViewById(R.id.TVFname);
            TVFname.setText(Fname);

            TVLname = (TextView)findViewById(R.id.TVLname);
            TVLname.setText(Lname);

            TVContact = (TextView)findViewById(R.id.TVContact);
            TVContact.setText(Contact);

            TVEmail = (TextView)findViewById(R.id.TVEmail);
            TVEmail.setText(Email);

            TVBirthdate = (TextView)findViewById(R.id.TVBirthdate);
            TVBirthdate.setText(Birthdate);

            //FetchedDataAcct process = new FetchedDataAcct();
            //process.execute();

            BtnLogout = (Button) findViewById(R.id.BtnLogout);
            BtnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Account2.this, Login.class);
                    FirebaseAuth.getInstance().signOut();
                    startActivity(i);
                }
            });
    }


    public void onEditAcct(View view){
        Intent i = new Intent(Account2.this, EditUser.class);
        startActivity(i);
    }
}
