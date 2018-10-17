package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            String allAds = "SELECT a.id, a.title, a.user_id, a.description, c.name AS 'category' FROM ads a JOIN ad_category ac ON ac.ad_id = a.id JOIN cats c ON c.id = ac.category_id";
            stmt = connection.prepareStatement(allAds);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Ad getAdById(Long id) {
//        Long num = id;
        try {
            String findAdQuery = "SELECT a.id, a.title, a.user_id, a.description, c.name AS 'category' FROM ads a JOIN ad_category ac ON ac.ad_id = a.id JOIN cats c ON c.id = ac.category_id WHERE a.id = ?";
//            String findQuery = "SELECT * FROM ads WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(findAdQuery, Statement.RETURN_GENERATED_KEYS);
//            System.out.println(id);
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return extractAd(rs);
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Error finding ad by id");
//            System.out.println("Could not find ad");
        }
    }

    @Override
    public List<Ad> getAdsByUser(User user) {
        List usersAds = new ArrayList<>();
        try {
//            Long id = user.getId();
            String searchQuery = "SELECT * FROM ads JOIN user_credentials ON ads.user_id = user_credentials.id WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(searchQuery);
            stmt.setLong(1, user.getId());
//            stmt.setString(2, term);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usersAds.add(extractAd(rs));
            }
            return usersAds;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Could not find ads by particular user");
            }
    }

    @Override
    public List<Ad> searchAdsByTitle(String term) {
        List<Ad> searchAds = new ArrayList<>();
        try {
            String searchQuery = "SELECT * FROM ads WHERE title LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(searchQuery);
            stmt.setString(1, "%" + term + "%");
//            stmt.setString(2, term);
            ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    searchAds.add(extractAd(rs));
                }
            return searchAds;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Error searching for term");
        }
    }

    @Override
    public List<Ad> searchAdsByDesc(String term) {
        List<Ad> searchAds = new ArrayList<>();
        try {
            String searchQuery = "SELECT * FROM ads WHERE description LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(searchQuery);
            stmt.setString(1, "%" + term + "%");
//            stmt.setString(2, term);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                searchAds.add(extractAd(rs));
            }
            return searchAds;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Error searching for term");
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(title, description, user_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ad.getTitle());
            stmt.setString(2, ad.getDescription());
            stmt.setLong(3, ad.getUserId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public void updatePost(String newTitle, String newDescription, Long id) {
        try {
            String updateQuery = "UPDATE ads SET title = ?, description = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(updateQuery);
            stmt.setString(1, newTitle);
            stmt.setString(2, newDescription);
            stmt.setLong(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Failed to update the users ad");
        }
    }

    @Override
    public void deleteAdById(Long id) {
        try {
            String deleteQuery = "DELETE FROM ads WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, id);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
//            ResultSet rs = stmt.execute(); WILL NOT WORK WITH DELETE
//            if (rs.next()) {
//                System.out.println("Delete was successful");
//            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Failed to delete ad by ID");
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getString("category")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

//    public String getAdCategory(ResultSet)
}
