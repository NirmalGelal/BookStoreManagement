package com.example.bookstore.Controller;

import com.example.bookstore.dto.request.LoginRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.User;
import com.example.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserServiceImpl userServiceImpl;
    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping ("/register")
    public Response registerUser(@RequestBody User user){
        return userServiceImpl.registerUser(user);
    }

    @GetMapping("/users")
    public Response findAll(){
        return  userServiceImpl.findAll();
    }
    @GetMapping("/users/{id}")
    public Response findById(@PathVariable int id){
        return  userServiceImpl.findById(id);
    }
    @PostMapping("/login")
    public Response loginUser(@RequestBody LoginRequestDto loginRequestDto){
        return userServiceImpl.loginUser(loginRequestDto);
    }

    @PostMapping("/update/{id}")
    public Response updateUser(@PathVariable int id, @RequestBody User user){
        return userServiceImpl.updateUser(id,user);
    }

}
