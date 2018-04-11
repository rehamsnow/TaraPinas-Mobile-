package com.example.ham.goralets.Messages;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ham.goralets.Account;
import com.example.ham.goralets.BookingStatus.BookingStatus;
import com.example.ham.goralets.BottomNavigationViewHelper;
import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.R;

public class MessageCat extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    LinearLayout BtnMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.homeItem){
                    Intent i = new Intent(MessageCat.this, MainActivity.class);
                    //i.putExtra("Email", str);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.bookingItem){
                    Intent i = new Intent(MessageCat.this, BookingStatus.class);
                    startActivity(i);
                }
                else if (item.getItemId() == R.id.messagesItem){
                    Intent i = new Intent(MessageCat.this, Messages.class);
                    startActivity(i);
                }
                else if(item.getItemId() == R.id.accountItem){
                    Intent i = new Intent(MessageCat.this, Account.class);
                    startActivity(i);
                }

                return false;
            }
        });


        BtnMsg = (LinearLayout) findViewById(R.id.BtnMsg);
        BtnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MessageCat.this, Messages.class);
                startActivity(i);
            }
        });
    }
}
