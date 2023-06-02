package com.example.bookstore.NewController;

import com.example.bookstore.model.User;
import com.example.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NewUserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> users = (List<User>) userServiceImpl.findAllUsers().getData();
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
    public String registerUser(Model model, @ModelAttribute User user){
        userServiceImpl.registerUser(user);
        return "redirect:/users";
    }
}
