package com.example.bookstore.service.impl;

import com.example.bookstore.model.User;
import com.example.bookstore.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean registerUser(User user) {
        return false;
    }

    @Override
    public User loginUser(String username, String password) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }
}
