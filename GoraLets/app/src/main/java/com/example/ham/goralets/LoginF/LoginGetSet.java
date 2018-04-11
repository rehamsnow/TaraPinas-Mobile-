package com.example.ham.goralets.LoginF;

/**
 * Created by Ham on 3/24/2018.
 */

public class LoginGetSet {
        private int user_id;
        private String user_fname;
        private String user_lname;
        private String user_email;
        private String user_contact;
        private String user_birthdate;

    public LoginGetSet(int user_id, String user_fname, String user_lname, String user_email, String user_contact, String user_birthdate){
        this.user_id = user_id;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_email = user_email;
        this.user_contact = user_contact;
        this.user_birthdate = user_birthdate;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public String getUser_birthdate() {
        return user_birthdate;
    }
}
