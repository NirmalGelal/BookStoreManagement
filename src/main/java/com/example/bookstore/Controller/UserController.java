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
        Response<String> response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        else if (request.getSession().getAttribute("userRole").equals("admin")){
            return  userServiceImpl.findAllUsers();
        }
        response.setMessage("User not authorized, please login as admin.");
        response.setData(null);
        return response;

    }
    @GetMapping("/users/{id}")
    public Response findUserById(@PathVariable int id, HttpServletRequest request){
        Response<String> response = new Response<>();
        if (request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        else if (request.getSession().getAttribute("userRole").equals("admin")){
            return  userServiceImpl.findUserById(id);
        }
        response.setMessage("User not authorized, please login by admin.");
        response.setData(null);
        return response;
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
        int userId = (Integer) request.getSession().getAttribute("userId");
        String role = (String) request.getSession().getAttribute("userRole");
        request.getSession().invalidate();
        Response response = new Response<>();
        response.setMessage("User Id: "+userId+", Role: "+ role +", logged out successfully");
        return response;
    }



    @PutMapping ("/update/{id}")
    public Response updateUser(@PathVariable int id, @RequestBody User user, HttpServletRequest request){
        Response<String> response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        else if(id==(Integer)request.getSession().getAttribute("userId") || request.getSession().getAttribute("userRole").equals("admin")){
            return userServiceImpl.updateUser(id,user);
        }
        response.setMessage("User not authorized to perform action");
        return response;
    }

}
