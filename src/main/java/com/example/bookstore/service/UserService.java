package com.example.bookstore.service;

import com.example.bookstore.dto.request.LoginRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.User;

public interface UserService {
    public Response registerUser(User user);
    public Response findAllUsers();
    public Response findUserById(int id);
    public Response loginUser(LoginRequestDto loginRequestDto);
    public Response updateUser(int id,User user);
}
