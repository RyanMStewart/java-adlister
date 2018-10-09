package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {
    private Connection connection = null;
    private List<User> users;

    MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed DB connection.");
        }
    }

    @Override
    public void createUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
//        try {
//            this.insert(user);
//        } catch (SQLException e) {
//            System.out.println("Could not insert user into database..");
//        }
//        try {
//            user.setId(insert(user));
//        } catch (SQLException e) {
//            System.out.println("Could not create ID for new user");
//        }
    }

    @Override
    public User findByUsername(String username) throws SQLException{
            users = new ArrayList<>();
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractUser(rs);
            }
        return extractUser(rs);
    }

    private User extractUser(ResultSet rs) throws SQLException{
        return new User(
                rs.getLong(1),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }
    @Override
    public Long insert(User user) {
        try {
            String ins = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(ins, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                rs.getLong(1);
            }
            return rs.getLong(1);
        } catch (SQLException e) {
           throw new RuntimeException("Could not add this user to the db");
        }
    }
}
