package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import org.mindrot.jbcrypt.BCrypt;

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

        if (password.length() < 8) {
            return false;
        } else {
            System.out.println("The length is good");
        }
        boolean hasNum = password.matches("[a-zA-Z ]*\\d+.*");

        if (!hasNum) {
            return false;
        } else {
            System.out.println("Number is good.");
        }
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
//                System.out.println("character not found");
            }
        }
        return true;
    }

    @Override
    public User getUserById(Long id) {
        String query = "SELECT * FROM user_credentials WHERE id = ?";
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
        String query = "UPDATE user_credentials SET username = ? WHERE username = ?";
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
    public void updatePassword(String password, User curUser) {
        System.out.println("Password going into hash: " + password);
        String username = curUser.getUsername();
        System.out.println("Username going into update: " + username);
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        String updatePwQuery = "UPDATE user_credentials SET password = ? WHERE username = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(updatePwQuery);
            stmt.setString(1, hash);
            stmt.setString(2, username);
            stmt.executeUpdate();
            System.out.println("We are reaching this point.");
        } catch (SQLException e) {
            System.out.println("Failed when updating password from EditProfile servlet");
            throw new RuntimeException("Could not update password");
        }
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM user_credentials WHERE username = ? LIMIT 1";
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
            String query = "SELECT username FROM user_credentials";
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
        String query = "INSERT INTO user_credentials(username, email, password) VALUES (?, ?, ?)";
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
