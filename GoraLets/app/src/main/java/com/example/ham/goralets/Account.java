package com.example.ham.goralets;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ham.goralets.BookingStatus.BookingStatus;
import com.example.ham.goralets.Messages.Messages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Account extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://bustap.myapc.edu.ph/Api_viewProfile2.php";

    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    String ParseResult;
    HashMap<String, String> ResultHash = new HashMap<>();
    String FinalJSonObject;
    TextView TVFname, TVLname, TVEmail, TVContact, TVBirthdate;
    String FnameHolder, LnameHolder, EmailHolder, ContactHolder, BirthdateHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.homeItem){
                    Intent i = new Intent(Account.this, MainActivity.class);
                    //i.putExtra("Email", str);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.bookingItem){
                    Intent i = new Intent(Account.this, BookingStatus.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.messagesItem){
                    Intent i = new Intent(Account.this, Messages.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.accountItem){
                    Intent i = new Intent(Account.this, Account.class);
                    startActivity(i);
                }

                return false;
            }
        });

        TVFname = (TextView)findViewById(R.id.TVFname);
        TVLname = (TextView)findViewById(R.id.TVLname);
        TVEmail = (TextView)findViewById(R.id.TVEmail);
        TVContact = (TextView)findViewById(R.id.TVContact);
        TVBirthdate = (TextView)findViewById(R.id.TVBirthdate);

        String email = getIntent().getStringExtra("Email");
        HttpWebCall(email);
    }

    public void HttpWebCall(final String email){
            class HttpWebCallFunction extends AsyncTask<String, Void, String>{
                @Override
                protected void onPreExecute(){
                    super.onPreExecute();
                    //pDialog = ProgressDialog.show(Account.this,"Loading Data", null, true, true );
                }

                @Override
                protected void onPostExecute(String httpResponseMsg){
                    super.onPostExecute(httpResponseMsg);
                    //pDialog.dismiss();

                    FinalJSonObject = httpResponseMsg;
                    new GetHttpResponse(Account.this).execute();
                }

                @Override
                protected String doInBackground(String... params) {
                    ResultHash.put("user_email", params[0]);
                    ParseResult = httpParse.postRequest(ResultHash, HttpURL);
                    return ParseResult;
                }
            }

            HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();
            httpWebCallFunction.execute(email);
    }
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>{
        public Context context;

        public GetHttpResponse(Context context){
            this.context = context;
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            try{
                if(FinalJSonObject != null){
                    JSONArray jsonArray = null;
                    try{
                        jsonArray = new JSONArray(FinalJSonObject);
                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++){
                            jsonObject = jsonArray.getJSONObject(i);

                            FnameHolder = jsonObject.getString("user_fname").toString();
                            LnameHolder = jsonObject.getString("user_lname").toString();
                            EmailHolder = jsonObject.getString("user_email").toString();
                            ContactHolder = jsonObject.getString("user_contact").toString();
                            BirthdateHolder = jsonObject.getString("user_birthdate").toString();
                        }
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            TVFname.setText(FnameHolder);
            TVLname.setText(LnameHolder);
            TVEmail.setText(EmailHolder);
            TVContact.setText(ContactHolder);
            TVBirthdate.setText(BirthdateHolder);
        }
    }

}
