package com.example.bookstore.service;

import com.example.bookstore.dto.request.LoginRequestDto;
import com.example.bookstore.model.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public List<User> findAll();
    public User findById(int id);
    public User loginUser(LoginRequestDto loginRequestDto);
    public User updateUser(int id,User user);
}
