package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {
    private Connection connection = null;
    private List<User> users;


    public User findByUsername(String username) throws SQLException{
        users = new ArrayList<>();
        String query = "SELECT username FROM users WHERE username=?";
        PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
        stmt.executeQuery();
        ResultSet rs = stmt.getGeneratedKeys();
        while (rs.next()) {
            rs.getLong(1);
        }
        return extractUser(rs);
    }

    private User extractUser(ResultSet rs) throws SQLException{
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    public Long insert(User user) throws SQLException{
        String ins = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(ins, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
        ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                rs.getLong(1);
            }
        return rs.getLong(1);
    }
}
