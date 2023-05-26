package com.example.bookstore.service.impl;

import com.example.bookstore.dto.request.LoginRequestDto;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(int id) {
        User user = userRepository.findById(id);
        if (user!=null){
            return user;
        }
        return null;
    }

    @Override
    public User loginUser(LoginRequestDto loginRequestDto) {

        User user1 = userRepository.findByUsername(loginRequestDto.getUsername());
        if(user1!=null && user1.getPassword().equals(loginRequestDto.getPassword())){
            return user1;
        }
        return null;
    }

    @Override
    public User updateUser(int id,User user) {
        User tempUser = userRepository.findById(id);
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(user.getPassword());
        tempUser.setEmail(user.getEmail());
        tempUser.setRole(user.getRole());
        return userRepository.save(tempUser);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
