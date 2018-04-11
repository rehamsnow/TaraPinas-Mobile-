package com.example.ham.goralets.BookingStatus;

/**
 * Created by Ham on 4/3/2018.
 */

public class BookingStatusGetSet{

        private int bookid;
        private String location;
        private String img1;
        private String status;
        private Double price;
        private String start;
        private String end;
        private int pax;
        private String fname;
        private String lname;
        private String email;
        private String contact;

        //id, status, price, start, end, pax, fname, lname, email, contact

        public BookingStatusGetSet(
                int bookid,
                String location,
                String img1,
                String status,
                Double price,
                String start,
                String end,
                int pax,
                String fname,
                String lname,
                String email,
                String contact){

            this.bookid = bookid;
            this.location = location;
            this.img1 = img1;
            this.status = status;
            this.price = price;
            this.start = start;
            this.end = end;
            this.pax = pax;
            this.fname = fname;
            this.lname = lname;
            this.email = email;
            this.contact = contact;
        }

    public int getBookid() {
        return bookid;
    }

    public String getLocation() {
        return location;
    }

    public String getImg1() {
        return img1;
    }

    public String getStatus() {
        return status;
    }

    public Double getPrice() {
        return price;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getPax() {
        return pax;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }
}
