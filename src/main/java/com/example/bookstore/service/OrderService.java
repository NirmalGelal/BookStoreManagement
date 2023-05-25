package com.example.bookstore.service;

import com.example.bookstore.model.Order;

public interface OrderService {
//    retrieving orders, updating order status, and processing payments
    public Order retrieveOrder();
    public void updateOrder();
    public void processPayment();
}
