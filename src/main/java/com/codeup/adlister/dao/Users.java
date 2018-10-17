package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    Long insert(User user);
    User getUserById(Long id);
    boolean checkUsernameDuplicates(String username);
    void updateUsername(String newUsername, User currentUser);
    void updatePassword(String password, User currentUser);
    boolean checkPasswordRequirements(String password);

}
