package com.example.bookstore.service;

import com.example.bookstore.model.User;

public interface UserService {
    public boolean registerUser(User user);
    public User loginUser(String username, String password);
    public boolean updateUser(User user);
}
