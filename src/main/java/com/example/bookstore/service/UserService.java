package com.example.bookstore.service;

import com.example.bookstore.dto.request.LoginRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.User;

import java.util.List;

public interface UserService {
    public Response registerUser(User user);
    public Response findAll();
    public Response findById(int id);
    public Response loginUser(LoginRequestDto loginRequestDto);
    public Response updateUser(int id,User user);
}
