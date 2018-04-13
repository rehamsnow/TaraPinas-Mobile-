package com.example.ham.goralets.EditAccount;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;

public class EditUser extends AppCompatActivity {

    EditText TFLnameUpdate,
             TFFnameUpdate,
             TFContactUpdate,
             TFBirthdateUpdate;

    Button BtnUpdateAcct;

    String EditLname, EditFname, EditContact, EditBirthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String ID = prefs.getString("ID", "no id"); //no id: default value
        String Fname = prefs.getString("Fname", "no fname");
        String Lname = prefs.getString("Lname", "no lname");
        String Contact = prefs.getString("Contact", "no contact");
        String Birthdate = prefs.getString("Birthdate", "no bdate");


        TFLnameUpdate = (EditText) findViewById(R.id.TFLnameUpdate);
        if (TFLnameUpdate == null){
            EditLname = Lname;
        }
        else{
            EditLname = TFLnameUpdate.getText().toString();
        }
        TFFnameUpdate  = (EditText) findViewById(R.id.TFFnameUpdate);
        if (TFFnameUpdate == null){
            EditFname = Fname;
        }
        else{
            EditFname = TFFnameUpdate.getText().toString();
        }
        TFContactUpdate = (EditText) findViewById(R.id.TFContactUpdate);
        if (TFContactUpdate == null){
            EditContact = Contact;
        }
        else{
            EditContact = TFContactUpdate.getText().toString();
        }
        TFBirthdateUpdate = (EditText) findViewById(R.id.TFBirthdateUpdate);
        if (TFBirthdateUpdate == null){
            EditBirthdate = Birthdate;
        }
        else{
            EditBirthdate = TFBirthdateUpdate.getText().toString();
        }

        BtnUpdateAcct = (Button) findViewById(R.id.BtnUpdateAcct);
        BtnUpdateAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData(EditLname, EditFname, EditBirthdate, EditContact);
            }
        });
    }
    public void InsertData(final String lname,
                           final String fname,
                           final String birthdate,
                           final String contact){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {

                String LnameHolder = lname;
                String FnameHolder = fname;
                String BirthdateHolder = birthdate;
                String ContactHolder = contact;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("user_lname", LnameHolder));
                nameValuePairs.add(new BasicNameValuePair("user_fname", FnameHolder));
                nameValuePairs.add(new BasicNameValuePair("user_birthdate", BirthdateHolder));
                nameValuePairs.add(new BasicNameValuePair("user_contact", ContactHolder));


                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(EditUser.this);
                final String ID = prefs.getString("ID", "no id");

                String ServerURL = "http://172.20.10.11/TaraPinas/API_EditAcct.php?user_id="+ID+"&user_fname="+FnameHolder+"&user_lname="+LnameHolder+"&user_birthdate="+BirthdateHolder+"&user_contact="+ContactHolder;

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
                Toast.makeText(EditUser.this, "Account Edited Successfully", Toast.LENGTH_LONG).show();

            }

        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(lname, fname, birthdate, contact);

    }
}
