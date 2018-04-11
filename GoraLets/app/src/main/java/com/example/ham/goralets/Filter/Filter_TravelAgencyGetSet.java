package com.example.ham.goralets.Filter;

/**
 * Created by Ham on 3/2/2018.
 */

public class Filter_TravelAgencyGetSet {
    private String img;
    private String name;
    private String info;
    private String contact;
    private int review;
    private int stars;

    public Filter_TravelAgencyGetSet (String img, String name, String info, String contact, int review, int stars){
        this.name = name;
        this.info = info;
        this.contact = contact;
        this.review = review;
        this.stars = stars;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getContact() {
        return contact;
    }

    public int getReview() {
        return review;
    }

    public int getStars() {
        return stars;
    }
}
