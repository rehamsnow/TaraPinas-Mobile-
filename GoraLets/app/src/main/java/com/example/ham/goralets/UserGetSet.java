package com.example.ham.goralets;

/**
 * Created by Ham on 2/24/2018.
 */

public class UserGetSet {
    private String email;
    private String fname;
    private String lname;
    private String contact;
    private String birthdate;

    public UserGetSet(String email, String fname, String lname, String contact, String birthdate) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.contact = contact;
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getContact() {
        return contact;
    }

    public String getBirthdate() {
        return birthdate;
    }
}
