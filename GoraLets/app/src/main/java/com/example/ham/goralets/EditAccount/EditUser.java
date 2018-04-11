package com.example.ham.goralets.EditAccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.ham.goralets.R;

import java.util.HashMap;

public class EditUser extends AppCompatActivity {

    EditText TFEmailUpdate,
            TFLnameUpdate,
            TFFnameUpdate,
            TFContactUpdate,
            TFBirthdateUpdate;
    String email, lname, fname, contact, birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        TFEmailUpdate = (EditText)findViewById(R.id.TFEmailUpdate);
        Intent i = getIntent();

        HashMap<String, String> item = (HashMap<String, String>)
                i.getSerializableExtra("user_email");
      //  email = item.get(ReadData.user_email);
        TFEmailUpdate.setText(email);
    }

   // public void update(View view){
      //  email = TFEmailUpdate.getText().toString();
      //  String update_url = ""
    }
//}
