package com.example.ham.goralets.LoginF;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ham.goralets.Account;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.R;
import com.example.ham.goralets.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    private EditText TFEmail, TFPassword;
    private FirebaseAuth auth;

    private Button BtnLogin, BtnRegister;
    private static final String TAG = "Login";

    List<LoginGetSet> LoginList;
    RecyclerView LoginRecyclerView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginRecyclerView = findViewById(R.id.LoginRecyclerView);
        LoginRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoginRecyclerView.setHasFixedSize(true);

        progressBar = findViewById(R.id.progressBar);

        LoginList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();

       if (auth.getCurrentUser() != null) {
           startActivity(new Intent(Login.this, MainActivity.class));
           finish();
        }

        BtnLogin = (Button) findViewById(R.id.BtnLogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Login();
            }
        });

        BtnRegister = (Button) findViewById(R.id.BtnRegister);
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });
    }

    private void Login() {

        TFEmail = (EditText) findViewById(R.id.TFEmail);
        TFPassword = (EditText) findViewById(R.id.TFPassword);

        final String email = TFEmail.getText().toString();
        final String password = TFPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Enter an email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Enter password.", Toast.LENGTH_SHORT).show();
            return;
        }

          auth.signInWithEmailAndPassword(email, password)
                 .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                     @Override

    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {

            final String URL = "http://magreport.myapc.edu.ph/TaraPinas/API_Login.php?user_email=" + email + "&user_pass=" + password;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray array = new JSONArray(response);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject establish = array.getJSONObject(i);

                                    Integer user_id = establish.getInt("user_id");
                                    String user_fname = establish.getString("user_fname");
                                    String user_lname = establish.getString("user_lname");
                                    String user_email = establish.getString("user_email");
                                    String user_contact = establish.getString("user_contact");
                                    String user_birthdate = establish.getString("user_birthdate");

                                    LoginGetSet LoginGetSet = new LoginGetSet(user_id, user_fname, user_lname, user_email, user_contact, user_birthdate);
                                    LoginList.add(LoginGetSet);
                                }

                                if (array.length() == 0) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Login.this, "Account is not registered.", Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    progressBar.setVisibility(View.GONE);
                                    finish();
                                }

                                LoginAdapter adapter = new LoginAdapter(Login.this, LoginList);
                                LoginRecyclerView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            Volley.newRequestQueue(Login.this).add(stringRequest);
        }
    }
       });

      }
}

    /*
    //private String URL = "http://192.168.100.7/TaraPinas/API_Login.php?user_email="+user_email+"&user_pass="+user_pass;
    private static final String TAG = "Login";
    private EditText TFEmail, TFPassword;
    private FirebaseAuth auth;

    private Button BtnLogin;
    private Button BtnRegister;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        // if (auth.getCurrentUser() != null) {
        //     startActivity(new Intent(Login.this, MainActivity.class));
        //     finish();
        // }

        TFEmail = (EditText) findViewById(R.id.TFEmail);
        TFPassword = (EditText) findViewById(R.id.TFPassword);

        BtnLogin = (Button) findViewById(R.id.BtnLogin);
        BtnRegister = (Button) findViewById(R.id.BtnRegister);

        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        BtnLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(final View view) {
                progressBar.setVisibility(View.VISIBLE);
                final String user_email = TFEmail.getText().toString();
                final String user_pass = TFPassword.getText().toString();

                auth.signInWithEmailAndPassword(user_email, user_pass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Intent i = new Intent(Login.this, Account.class);
                                    i.putExtra("Email", user_email);

                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    progressBar.setVisibility(View.GONE);
                                    finish();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Email or password incorrect, try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
                /*
                final String URL = "http://192.168.100.7/TaraPinas/API_Login.php?user_email="+user_email+"&user_pass="+user_pass;
                if (TextUtils.isEmpty(user_email)) {
                    Toast.makeText(getApplicationContext(), "Enter an email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(user_pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                login();

            }
        });
    }


        private void login(){

            StringRequest stringRequest = new StringRequest(Request.Method., URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray array = new JSONArray(response);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject user = array.getJSONObject(i);

                                    String user_fname = user.getString("user_fname");
                                    String user_lname = user.getString("user_lname");
                                    String user_contact = user.getString("user_contact");
                                    String user_birthdate = user.getString("user_birthdate");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();}
                        }
                    }
            );
        }

    }
                */

