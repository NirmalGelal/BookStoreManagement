package com.example.bookstore.service;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.ShoppingCart;

import java.util.List;


public interface ShoppingCartService {
    public Response<ShoppingCart> addToCart(int bookId, int userId);
    public Response<ShoppingCart> removeFromCart(int bookId, int userId);
    public Response clearCart(int userId);
    public double calculateTotalAmount(List<Book> books);

}
