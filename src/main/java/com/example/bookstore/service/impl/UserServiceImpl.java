package com.example.bookstore.service.impl;

import com.example.bookstore.dto.request.LoginRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Response registerUser(User user) {
        Response<User> response = new Response<>();
        response.setMessage("User registered successfully");
        response.setData(userRepository.save(user));
        return response;
    }

    @Override
    public Response findUserById(int id) {
        Response<User> response = new Response<>();
        User user = userRepository.findById(id);
        if (user!=null){
            response.setMessage("User retrieved successfully");
            response.setData(user);
            return response;
        }
        response.setMessage("User not found");
        response.setData(null);
        return response;
    }

    @Override
    public Response loginUser(LoginRequestDto loginRequestDto) {
        Response<User> response = new Response<>();
        User user1 = userRepository.findByUsername(loginRequestDto.getUsername());
        if(user1!=null && user1.getPassword().equals(loginRequestDto.getPassword())){
            response.setData(user1);
            response.setMessage("Logged in successfully");
            return response;
        }
        response.setMessage("Incorrect username or password");
        response.setData(null);
        return response;
    }

    @Override
    public Response updateUser(int id,User user) {
        Response<User> response = new Response<>();
        if(userRepository.findById(id)!=null){
            User tempUser = userRepository.findById(id);
            tempUser.setUsername(user.getUsername());
            tempUser.setPassword(user.getPassword());
            tempUser.setEmail(user.getEmail());
            tempUser.setRole(user.getRole());
            response.setMessage("User updated successfully");
            response.setData(userRepository.save(tempUser));
            return response;
        }
        response.setMessage("Invalid id");
        return response;

    }

    @Override
    public Response findAllUsers(int page, int size, String sortBy) {
        Response<Page<User>> response = new Response<>();
        response.setMessage("Users retrieved successfully");
        Page<User> users = userRepository.findAll(PageRequest.of(page,size, Sort.by(sortBy)));
        response.setData(users);
        return response;
    }
}
