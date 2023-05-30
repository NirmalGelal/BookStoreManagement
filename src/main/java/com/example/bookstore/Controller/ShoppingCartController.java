package com.example.bookstore.Controller;

import com.example.bookstore.dto.request.AddToCartRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.ShoppingCart;
import com.example.bookstore.service.impl.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {

    private ShoppingCartServiceImpl shoppingCartServiceImpl;

    @Autowired
    public ShoppingCartController(ShoppingCartServiceImpl shoppingCartServiceImpl) {
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
    }

    @PostMapping("/add-to-cart")
    public Response<ShoppingCart> addToCart(@RequestBody AddToCartRequestDto addToCartRequestDto){
        return shoppingCartServiceImpl.addToCart(addToCartRequestDto.getBookIds(), addToCartRequestDto.getUserId());
    }

    @DeleteMapping ("/delete-from-cart/{id}")
    public Response<ShoppingCart> deleteFromCart(@PathVariable int id){
        return shoppingCartServiceImpl.removeFromCart(id);
    }
}
