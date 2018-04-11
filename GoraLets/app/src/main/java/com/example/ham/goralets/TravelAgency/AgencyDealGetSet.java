package com.example.ham.goralets.TravelAgency;

/**
 * Created by Ham on 4/9/2018.
 */

public class AgencyDealGetSet {

    private String desc;

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

    public AgencyDealGetSet(String desc, String img, String img2, String img3, String title, String startdate, String enddate, Double price, String inclusions, String exclusions){

        this.desc = desc;
        this.img = img;
        this.img2 = img2;
        this.img3 = img3;

        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.price = price;

        this.inclusions = inclusions;
        this.exclusions = exclusions;


    }

    public String getDesc() {
        return desc;
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




}
