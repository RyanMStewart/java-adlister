package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();

    List<String> allCats();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    Ad getAdById(Long id);

    List<Ad> searchAdsByTitle(String term);

    List<Ad> searchAdsByDesc(String term);

    List<Ad> getAdsByUser(User user);

    List<Ad> getAdsByCategory(String name);

    void deleteAdById(Long id);

    void updatePost(String newTitle, String newDescription, Long id);
}
