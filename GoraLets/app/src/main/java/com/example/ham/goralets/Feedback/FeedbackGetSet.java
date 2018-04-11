package com.example.ham.goralets.Feedback;

/**
 * Created by Ham on 3/23/2018.
 */

public class FeedbackGetSet {

    private String fname;
    private String lname;
    private int feedbackrating;
    private String comment;
    private String feedbackimg;

    public FeedbackGetSet(String fname, String lname, int feedbackrating, String comment, String feedbackimg)
    {
        this.fname = fname;
        this.lname = lname;
        this.feedbackrating = feedbackrating;
        this.comment = comment;
        this.feedbackimg = feedbackimg;
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

    public String getFeedbackimg() {
        return feedbackimg;
    }
}
