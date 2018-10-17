package com.codeup.adlister.dao;

public class Config {
    private String url, user, password;

    public String getUrl() {
        return "jdbc:mysql://localhost/ad_lister_db?serverTimezone=UTC&useSSL=false";
    }

    public String getUser() {
        return "root";
    }

    public String getPassword() {
        return "drowssap";
    }
}
