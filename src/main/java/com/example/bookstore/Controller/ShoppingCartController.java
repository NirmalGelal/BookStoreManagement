package com.example.bookstore.Controller;

import com.example.bookstore.dto.request.AddToCartRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.ShoppingCart;
import com.example.bookstore.service.impl.ShoppingCartServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ShoppingCartController {

    private ShoppingCartServiceImpl shoppingCartServiceImpl;

    @Autowired
    public ShoppingCartController(ShoppingCartServiceImpl shoppingCartServiceImpl) {
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
    }

    @PostMapping("/add-to-cart/{bookId}")
    public Response<ShoppingCart> addToCart(@PathVariable int bookId, HttpServletRequest request){
        Response response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        return shoppingCartServiceImpl.addToCart(bookId, (Integer) request.getSession().getAttribute("userId"));
    }

    @DeleteMapping ("/delete-from-cart/{bookId}")
    public Response<ShoppingCart> deleteFromCart(@PathVariable int bookId, HttpServletRequest request){
        Response response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        return shoppingCartServiceImpl.removeFromCart(bookId, (Integer)request.getSession().getAttribute("userId"));
    }

    @DeleteMapping("clear-cart")
    public Response clearCart(HttpServletRequest request){
        Response response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        return shoppingCartServiceImpl.clearCart((Integer) request.getSession().getAttribute("userId"));
    }
}
