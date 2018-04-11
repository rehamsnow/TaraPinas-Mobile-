package com.example.ham.goralets;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.ham.goralets.LoginF.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String ServerURL = "http://magreport.myapc.edu.ph/TaraPinas/API_AddUser.php";
    private static final String TAG = "activity_registration";
    private EditText    TFEmailRegister,
                        TFPasswordRegister,
                        TFRpasswordRegister,
                        TFFnameRegister,
                        TFLnameRegister,
                        TFContactRegister,
                        TFBirthdateRegister;

                String  Fname_holder,
                        Lname_holder,
                        Birthdate_holder,
                        Email_holder,
                        Contact_holder,
                        Pass_holder,
                        Rpass_holder;

    private Button BtnRegister;
    private ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        TFEmailRegister = (EditText) findViewById(R.id.TFEmailRegister);
        TFFnameRegister = (EditText) findViewById(R.id.TFFnameRegister);
        TFLnameRegister = (EditText) findViewById(R.id.TFLnameRegister);
        TFContactRegister = (EditText) findViewById(R.id.TFContactRegister);
        TFPasswordRegister = (EditText) findViewById(R.id.TFPasswordRegister);
        TFRpasswordRegister = (EditText) findViewById(R.id.TFRpasswordRegister);
        TFBirthdateRegister = (EditText) findViewById(R.id.TFBirthdateRegister);

        BtnRegister = (Button) findViewById(R.id.BtnSubmitRegister);

        TFEmailRegister = (EditText) findViewById(R.id.TFEmailRegister);
        TFPasswordRegister = (EditText) findViewById(R.id.TFPasswordRegister);


        BtnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                String email = TFEmailRegister.getText().toString();
                String password = TFPasswordRegister.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter an email address.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { //email must be a valid account!
                    Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Password too short.",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(Registration.this, "Authentication failed, email is already in use.", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                                else{
                                    getData();
                                    InsertData(Fname_holder, Lname_holder, Email_holder, Birthdate_holder, Contact_holder, Pass_holder);
                                    Log.d(TAG, "InsertData successful.");
                                    startActivity(new Intent(Registration.this, Login.class));
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });
    }

    public void getData(){
        Fname_holder = TFFnameRegister.getText().toString();
        Lname_holder = TFLnameRegister.getText().toString();
        Email_holder = TFEmailRegister.getText().toString();
        Birthdate_holder = TFBirthdateRegister.getText().toString();
        Contact_holder = TFContactRegister.getText().toString();
        Pass_holder = TFPasswordRegister.getText().toString();
        Rpass_holder = TFRpasswordRegister.getText().toString();
    }
    public void InsertData(final String TFFnameRegister,
                           final String TFLnameRegister,
                           final String TFEmailRegister,
                           final String TFBirthdateRegister,
                           final String TFContactRegister,
                           final String TFPasswordRegister){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... strings) {
                String NameHolder = TFFnameRegister;
                String LnameHolder = TFLnameRegister;
                String EmailHolder = TFEmailRegister;
                String BirthdateHolder = TFBirthdateRegister;
                String ContactHolder = TFContactRegister;
                String PassHolder = TFPasswordRegister;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("user_fname", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("user_lname", LnameHolder));
                nameValuePairs.add(new BasicNameValuePair("user_email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("user_birthdate", BirthdateHolder));
                nameValuePairs.add(new BasicNameValuePair("user_contact", ContactHolder));
                nameValuePairs.add(new BasicNameValuePair("user_pass", PassHolder));

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
                Toast.makeText(Registration.this, "Data Submitted Successfully", Toast.LENGTH_LONG).show();

            }

            }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(TFFnameRegister, TFLnameRegister, TFEmailRegister, TFBirthdateRegister, TFContactRegister, TFPasswordRegister);

    }
    }
