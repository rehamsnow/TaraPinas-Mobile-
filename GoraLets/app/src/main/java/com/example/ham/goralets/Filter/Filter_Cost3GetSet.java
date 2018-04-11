package com.example.ham.goralets.Filter;

/**
 * Created by Ham on 3/2/2018.
 */

public class Filter_Cost3GetSet  {
    private String img;
    private Double price;
    private String title;
    private int review;

    public Filter_Cost3GetSet(String img, String title, Double price, int review){
        this.img = img;
        this.price = price;
        this.title = title;
        this.review = review;
    }

    public String getImg() {
        return img;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int getReview() {
        return review;
    }
}
