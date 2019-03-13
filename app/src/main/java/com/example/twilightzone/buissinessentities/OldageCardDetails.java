package com.example.twilightzone.buissinessentities;

public class OldageCardDetails {
    private String title;
    private String details;
    private String request;
    private String userid;



    public OldageCardDetails(String title, String details, String request, String userid) {
        this.title = title;
        this.details = details;
        this.request = request;
        this.userid = userid;
    }


    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getRequest() {
        return request;
    }
    public String getUserid()  { return userid;  }
}
