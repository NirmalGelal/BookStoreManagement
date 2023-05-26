package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.ShoppingCart;



public interface ShoppingCartService {
    public void addToCart(Book book);
    public void removeFromCart(Book book);
    public double calculateTotalAmount(Book book);
    public void placeOrder(Book book);
    public void processPayment(ShoppingCart shoppingCart);

}
