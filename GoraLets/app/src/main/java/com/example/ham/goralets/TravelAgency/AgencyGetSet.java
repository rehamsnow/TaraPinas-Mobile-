package com.example.ham.goralets.TravelAgency;

/**
 * Created by Ham on 4/9/2018.
 */

public class AgencyGetSet  {

    private String fname;
    private String lname;
    private int feedbackrating;
    private String comment;


    public AgencyGetSet(String fname, String lname, int feedbackrating, String comment)
    {
        this.fname = fname;
        this.lname = lname;
        this.feedbackrating = feedbackrating;
        this.comment = comment;

    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getFeedbackrating() {
        return feedbackrating;
    }

    public String getComment() {
        return comment;
    }

}
