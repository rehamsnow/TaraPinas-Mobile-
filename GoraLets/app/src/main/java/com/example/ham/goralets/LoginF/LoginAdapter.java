package com.example.ham.goralets.LoginF;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ham.goralets.MainActivity;
import com.example.ham.goralets.R;

import java.util.List;

/**
 * Created by Ham on 3/24/2018.
 */

public class LoginAdapter extends RecyclerView.Adapter<LoginAdapter.LoginViewHolder>{

    private Context mCtx;
    private List<LoginGetSet> LoginList;

    private static final String TAG = "LoginAdapter";

    public LoginAdapter(Context mCtx, List<LoginGetSet> LoginList){
        this.mCtx = mCtx;
        this.LoginList = LoginList;
    }

    @Override
    public LoginAdapter.LoginViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_login_details,null);
        return new LoginAdapter.LoginViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoginAdapter.LoginViewHolder holder, int position) {
        final LoginGetSet LoginGetSet = LoginList.get(position);

        holder.AccID.setText(String.valueOf(LoginGetSet.getUser_id()));
        holder.AccFname.setText(LoginGetSet.getUser_fname());
        holder.AccLname.setText(LoginGetSet.getUser_lname());
        holder.AccEmail.setText(LoginGetSet.getUser_email());
        holder.AccContact.setText(LoginGetSet.getUser_contact());
        holder.AccBdate.setText(LoginGetSet.getUser_birthdate());

        /*
        Intent intent = new Intent(mCtx, MainActivity.class);
        intent.putExtra("ID", (String.valueOf(LoginGetSet.getUser_id())));
        intent.putExtra("Fname",  LoginGetSet.getUser_fname());
        intent.putExtra("Lname",  LoginGetSet.getUser_lname());
        intent.putExtra("Email", LoginGetSet.getUser_email());
        intent.putExtra("Contact",  LoginGetSet.getUser_contact());
        intent.putExtra("Bdate",  LoginGetSet.getUser_birthdate());
        */

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("ID", String.valueOf(LoginGetSet.getUser_id()));
        editor.putString("Fname", LoginGetSet.getUser_fname());
        editor.putString("Lname", LoginGetSet.getUser_lname());
        editor.putString("Email", LoginGetSet.getUser_email());
        editor.putString("Contact", LoginGetSet.getUser_contact());
        editor.putString("Birthdate", LoginGetSet.getUser_birthdate());

        editor.commit();
        //mCtx.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return  LoginList.size();
    }

    class  LoginViewHolder extends RecyclerView.ViewHolder{

        TextView AccFname, AccLname, AccContact, AccBdate, AccID, AccEmail;

        public LoginViewHolder(View itemView) {
            super(itemView);

            AccID = itemView.findViewById(R.id.AccID);
            AccFname = itemView.findViewById(R.id.AccFname);
            AccLname = itemView.findViewById(R.id.AccLname);
            AccEmail = itemView.findViewById(R.id.AccEmail);
            AccContact = itemView.findViewById(R.id.AccContact);
            AccBdate = itemView.findViewById(R.id.AccBdate);

        }
    }
}

