package com.example.bookstore.service;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.ShoppingCart;

import java.util.List;


public interface ShoppingCartService {
    public Response<ShoppingCart> addToCart(List<Integer> book_ids, int user_id);
    public Response<ShoppingCart> removeFromCart(int shoppingCartId);
    public double calculateTotalAmount(List<Book> books);

}
