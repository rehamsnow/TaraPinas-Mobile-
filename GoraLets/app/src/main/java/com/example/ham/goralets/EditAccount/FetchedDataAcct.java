package com.example.ham.goralets.EditAccount;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.ham.goralets.Account2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ham on 3/1/2018.
 */

public class FetchedDataAcct extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    String email2 = "snoow.029@gmail.com";
    String fname="";
    String lname = "";
    String email = "";
    String contact = "";
    String birthdate = "";
    String lname2="";
    String email3 = "";

    String fname_holder, lname_holder, contact_holder, birthdate_holder,email_holder;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://bustap.myapc.edu.ph/Api_viewProfile3.php");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) //edit this to while line has this email <<email@gmail>> or if method.
            {
                line = bufferedReader.readLine(); //reading per line of Json file.
                data = data + line; //stores data to 'data'
            }

            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                     email = JO.getString("user_email");
                     if(email.equals(email2)) { //how to get this freaking string!
                         email3 = email;
                         fname = JO.getString("user_fname");
                         lname = JO.getString("user_lname");
                         contact = JO.getString("user_contact");
                         birthdate = JO.getString("user_birthdate");

                     }

            }

        }catch(MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);

        //Account2.data.setText(this.dataParsed);
        Account2.TVFname.setText(this.fname);
        Account2.TVLname.setText(this.lname);
        Account2.TVContact.setText(this.contact);
        Account2.TVEmail.setText(this.email3);
        Account2.TVBirthdate.setText(this.birthdate);
    }
}
