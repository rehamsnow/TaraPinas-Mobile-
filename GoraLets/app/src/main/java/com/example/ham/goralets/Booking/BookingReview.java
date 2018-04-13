package com.example.ham.goralets.Booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ham.goralets.Account;
import com.example.ham.goralets.Deals.DealLayout;
import com.example.ham.goralets.MainActivity;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookingReview extends AppCompatActivity {

    private static final String TAG = "BookingReview";
    String ServerURL = "http://magreport.myapc.edu.ph/TaraPinas/API_Book.php";
    TextView RVLocation, RVStartdate, RVEnddate, RVDuration, RVPax, RVPrice, RVFname, RVLname, RVEmail, RVContact;
    Button BtnSubmit, BtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_review);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String ID = prefs.getString("ID", "no id"); //no id: default value
        final String DealID = prefs.getString("DealID", "no deal ID"); //no id: default value

        final String Fname = getIntent().getStringExtra("cFname");
        final String Lname = getIntent().getStringExtra("cLname");
        final String Contact = getIntent().getStringExtra("cContact");
        final String Email = getIntent().getStringExtra("cEmail");

        RVFname = (TextView) findViewById(R.id.RVFname);
        RVLname = (TextView) findViewById(R.id.RVLname);
        RVEmail = (TextView) findViewById(R.id.RVEmail);
        RVContact = (TextView) findViewById(R.id.RVContact);

        RVFname.setText(Fname);
        RVLname.setText(Lname);
        RVEmail.setText(Email);
        RVContact.setText(Contact);

        String Title = getIntent().getStringExtra("Title");
        final String Startdate = getIntent().getStringExtra("Startdate");
        final String Enddate = getIntent().getStringExtra("Enddate");
        final String Pax = getIntent().getStringExtra("Pax");
        String Duration = getIntent().getStringExtra("Duration");

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
        final String FINALPRICE = prefs2.getString("FINALPRICE", "0");

        RVLocation = (TextView) findViewById(R.id.RVLocation);
        RVStartdate = (TextView) findViewById(R.id.RVStartdate);
        RVEnddate = (TextView) findViewById(R.id.RVEnddate);
        RVDuration = (TextView) findViewById(R.id.RVDuration);
        RVPax = (TextView) findViewById(R.id.RVPax);
        RVPrice = (TextView) findViewById(R.id.RVPrice);

        RVPax.setText(Pax);
        RVLocation.setText(Title);
        RVStartdate.setText(Startdate);
        RVEnddate.setText(Enddate);
        RVDuration.setText(Duration);
        RVPrice.setText(FINALPRICE);

        final String Status = "PENDING";

        BtnSubmit = (Button) findViewById(R.id.BtnSubmit);
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = getIntent().getStringExtra("cEmail");
                Log.d(TAG, "Email: " + Email);

                //getData();
                InsertData(ID, DealID, Status, FINALPRICE, Startdate, Enddate, Pax, Fname, Lname, Email, Contact);

                Intent i = new Intent(BookingReview.this, BookingMsg.class);
                i.putExtra("cEmail", Email);
                startActivity(i);

            }
        });

        BtnCancel = (Button) findViewById(R.id.BtnCancel);
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookingReview.this, Bookings.class);
                startActivity(i);
            }
        });
    }


    public void InsertData(final String ID,
                           final String DealID,
                           final String Status,
                           final String Price,
                           final String Startdate,
                           final String Enddate,
                           final String Pax,
                           final String Fname,
                           final String Lname,
                           final String Email,
                           final String Contact){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                String IDHolder = ID;
                String DealIDHolder = DealID;
                String StatusHolder = Status;
                String PriceHolder = Price;
                String StartdateHolder = Startdate;
                String EnddateHolder = Enddate;
                String PaxHolder = Pax;
                String FnameHolder = Fname;
                String LnameHolder = Lname;
                String EmailHolder = Email;
                String ContactHolder = Contact;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("user_id", IDHolder));
                nameValuePairs.add(new BasicNameValuePair("deal_id", DealIDHolder));
                nameValuePairs.add(new BasicNameValuePair("book_status", StatusHolder));
                nameValuePairs.add(new BasicNameValuePair("book_price", PriceHolder));
                nameValuePairs.add(new BasicNameValuePair("book_start", StartdateHolder));
                nameValuePairs.add(new BasicNameValuePair("book_end", EnddateHolder));
                nameValuePairs.add(new BasicNameValuePair("book_pax", PaxHolder));
                nameValuePairs.add(new BasicNameValuePair("book_fname", FnameHolder));
                nameValuePairs.add(new BasicNameValuePair("book_lname", LnameHolder));
                nameValuePairs.add(new BasicNameValuePair("book_email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("book_contact", ContactHolder));

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
                Toast.makeText(BookingReview.this, "Data Submitted Successfully", Toast.LENGTH_LONG).show();

            }

        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(ID, DealID, Status, Price, Startdate, Enddate, Pax, Fname, Lname, Email, Contact);

    }
}
