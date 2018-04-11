package com.example.ham.goralets.Deals;

/**
 * Created by Ham on 2/23/2018.
 */

public class DealsGetSet {

    private int id;
    private String img;
    private String img2;
    private String img3;

    private String title;
    private String startdate;
    private String enddate;

    private Double price;

    private int rating;
    private String inclusions;
    private String exclusions;
    private String agency;

    public DealsGetSet(int id, String img, String img2, String img3, String title, String startdate, String enddate, Double price, int rating, String inclusions, String exclusions, String agency){
        this.id = id;

        this.img = img;
        this.img2 = img2;
        this.img3 = img3;

        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.price = price;

        this.rating = rating;
        this.inclusions = inclusions;
        this.exclusions = exclusions;

        this.agency = agency;


    }

    public int getId() {
        return id;
    }

    public String getImg2() {
        return img2;
    }

    public String getImg3() {
        return img3;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getInclusions() {
        return inclusions;
    }

    public String getExclusions() {
        return exclusions;
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

    public int getRating() {
        return rating;
    }

    public String getAgency() {
        return agency;
    }
}
