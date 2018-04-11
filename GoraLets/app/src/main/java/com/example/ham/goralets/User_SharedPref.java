package com.example.ham.goralets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ham.goralets.LoginF.Login;

/**
 * Created by Ham on 2/24/2018.
 */

public class User_SharedPref {

    private static final String TFEmail = "email";
    private static final String TFFname = "fname";
    private static final String TFLname = "lname";
    private static final String TFContact = "contact";
    private static final String TFBirthdate = "birthdate";

    private static User_SharedPref mInstance;
    private static Context mCtx;

    private User_SharedPref(Context context){
        mCtx = context;
    }

    public static synchronized User_SharedPref getmInstance(Context context){
        if(mInstance == null){
            mInstance = new User_SharedPref(context);
        }
        return mInstance;
    }

    public void userLogin(UserGetSet user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(TFEmail, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TFEmail, user.getEmail());
        editor.putString(TFFname, user.getFname());
        editor.putString(TFLname, user.getLname());
        editor.putString(TFContact, user.getContact());
        editor.putString(TFBirthdate, user.getBirthdate());
        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(TFEmail, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TFEmail, null) != null;
    }

    public UserGetSet getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(TFEmail, Context.MODE_PRIVATE);
        return new UserGetSet(
                sharedPreferences.getString(TFEmail, null),
                sharedPreferences.getString(TFFname, null),
                sharedPreferences.getString(TFLname,null),
                sharedPreferences.getString(TFContact,null),
                sharedPreferences.getString(TFBirthdate, null));
    }

    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(TFEmail, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login.class));
    }
}
