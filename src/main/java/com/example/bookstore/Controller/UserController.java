package com.example.bookstore.Controller;

import com.example.bookstore.dto.request.LoginRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.User;
import com.example.bookstore.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public Response findAllUsers(HttpServletRequest request){
        String role = (String)request.getSession().getAttribute("userRole");
        if (role!=null && role.equals("admin")){
            return  userServiceImpl.findAllUsers();
        }
        else {
            Response<String> response = new Response<>();
            response.setMessage("User not authorized, please login first.");
            response.setData(null);
            return response;
        }
    }
    @GetMapping("/users/{id}")
    public Response findUserById(@PathVariable int id, HttpServletRequest request){
        String role = (String)request.getSession().getAttribute("userRole");
        if (role!=null && role.equals("admin")){
            return  userServiceImpl.findUserById(id);
        }
        else {
            Response<String> response = new Response<>();
            response.setMessage("User not authorized, please login first.");
            response.setData(null);
            return response;
        }
    }
    @PostMapping("/login")
    public Response loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request){
        Response<User> response = userServiceImpl.loginUser(loginRequestDto);
        User user = response.getData();
        if (user!=null){
            request.getSession().setAttribute("userRole",user.getRole());
            request.getSession().setAttribute("userId", user.getId());
            return response;
        }
        return response;

    }

    @PostMapping("/logout")
    public Response logout(HttpServletRequest request){
        request.getSession().invalidate();
        Response response = new Response<>();
        response.setMessage("logged out successfully");
        return response;
    }



    @PutMapping ("/update/{id}")
    public Response updateUser(@PathVariable int id, @RequestBody User user, HttpServletRequest request){

        if(id==(Integer)request.getSession().getAttribute("userId") || request.getSession().getAttribute("userRole").equals("admin")){
            return userServiceImpl.updateUser(id,user);
        }
        Response<String> response = new Response<>();
        response.setMessage("You cannot update information about other user unless you are admin.");
        return response;
    }

}
