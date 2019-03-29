package com.example.twilightzone.buissinessentities;

public class PublicRegEntity {

    private String password;
    private String name ;
    private String address;
    private String contact ;
    private String email;

    public PublicRegEntity(String password, String name, String address, String contact, String email) {
        this.password = password;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
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

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }
}
