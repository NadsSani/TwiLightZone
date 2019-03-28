package com.example.twilightzone.buissinessentities;

public class OldRegisEntity {

    private String name;
    private String address;
    private String membercount;
    private String income;
    private String regno;
    private String oldage;
    private String daycare;
    private String password;
    private String contact;
    private String email;
    private String desc;
    private String details;


    public OldRegisEntity(String name, String address, String membercount, String income, String regno, String oldage, String daycare, String password, String contact, String email, String desc, String details) {
        this.name = name;
        this.address = address;
        this.membercount = membercount;
        this.income = income;
        this.regno = regno;
        this.oldage = oldage;
        this.daycare = daycare;
        this.password = password;
        this.contact = contact;
        this.email = email;
        this.desc = desc;
        this.details = details;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMembercount() {
        return membercount;
    }

    public String getIncome() {
        return income;
    }

    public String getRegno() {
        return regno;
    }

    public String getOldage() {
        return oldage;
    }

    public String getDaycare() {
        return daycare;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getDesc() {
        return desc;
    }

    public String getDetails() {
        return details;
    }
}
