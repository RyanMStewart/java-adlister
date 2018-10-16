package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
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

    public boolean checkPasswordRequirements(String password) {
        // Needs at least one number
        // Needs at least one symbol
        // Must be at least 8 characters long
//        String[] passwordString = new String[password.length()];
        if (password.length() < 8) {
            return false;
        } else {
            System.out.println("The length is good");
        }
        System.out.println(password.length());
//        char[] specialChars = {'!', };
        boolean hasNum = password.matches("[a-zA-Z ]*\\d+.*");

        if (!hasNum) {
            System.out.println("There was no number in that password");
            return false;
        } else {
            System.out.println("Number is good.");
        }
        //        boolean hasSymbol = password.matches("[a-zA-Z0-9]*");
        char[] charas = password.toCharArray();
        System.out.println(charas);
        for (int x = 0; x < password.length(); x++) {
//            String currentChar = password.charAt(x);
            System.out.println(password.charAt(x));
            if (password.charAt(x) == '!') {
                return true;
            } else if (password.charAt(x) == '@') {
                return true;
            } else if (password.charAt(x) == '#') {
                return true;
            } else if (password.charAt(x) == '$') {
                return true;
            } else if (password.charAt(x) == '%') {
                return true;
            } else if (password.charAt(x) == '^') {
                return true;
            } else if (password.charAt(x) == '&') {
                return true;
            } else if (password.charAt(x) == '*') {
                return true;
            } else {
                System.out.println("character not found");
            }
        }
//        System.out.println("Has symbol: " + hasSymbol);


//        try {
//            Long num = Long.parseLong(password);
//        } catch (NumberFormatException e) {
//            System.out.println("There was no number");
//            return false;
//        }

//            if (num == null) {
//                System.out.println("There is no number");
//                return false;
//            }
        return true;
    }

    @Override
    public User getUserById(Long id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by id");
        }
    }

    @Override
    public void updateUsername(String newUsername, User curUser) {
        String query = "UPDATE users SET username = ? WHERE username = ?";
        String currentName = curUser.getUsername();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, newUsername);
            stmt.setString(2, currentName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Could not update username");
        }
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public boolean checkUsernameDuplicates(String username) {
        try {
            List<String> usernameList = new ArrayList<>();
            String query = "SELECT username FROM users";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usernameList.add(rs.getString("username"));
            }
                for (String name : usernameList) {
                    if (name.equalsIgnoreCase(username)) {
                        System.out.println("Found matching username");
                        return true;
                    }
                }
                return false;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Something went wrong when checking for duplicate usernames");
        }
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password")
        );
    }

}
