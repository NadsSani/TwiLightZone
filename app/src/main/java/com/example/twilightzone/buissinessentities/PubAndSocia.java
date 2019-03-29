package com.example.twilightzone.buissinessentities;

public class PubAndSocia {
    private String userid;
    private String password;
    private String name;
    private String address;
    private String email;
    private String contact;


    public PubAndSocia(String userid, String password, String name, String address, String email, String contact) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.contact = contact;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }
}
