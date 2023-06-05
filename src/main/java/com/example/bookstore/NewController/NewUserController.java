package com.example.bookstore.NewController;

import com.example.bookstore.model.User;
import com.example.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NewUserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public String getAllUsers(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "id") String sortBy){
        Page<User> users = (Page<User>) (userServiceImpl.findAllUsers(page-1, size, sortBy).getData());
        model.addAttribute("users",users);
        return "/userList";
    }

    @GetMapping("/update-user-form/{id}")
    public String userUpdateForm(Model model, @PathVariable int id){
        User user = (User) userServiceImpl.findUserById(id).getData();
        model.addAttribute("user",user);
        return "/updateUserForm";
    }

    @PostMapping("/update-user")
    public String updateUser(Model model, @ModelAttribute User user){
        userServiceImpl.registerUser(user);
        return "redirect:/users";
    }

    @PostMapping ("/register-user")
    public String registerUserForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "/registerUserForm";
    }

}
