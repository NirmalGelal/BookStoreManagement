package com.example.bookstore.service;

import com.example.bookstore.dto.request.OrderRequestDto;
import com.example.bookstore.model.Order;


public interface OrderService {
//    retrieving orders, updating order status, and processing payments
//    Order placeOrder(OrderRequestDto orderRequestDto);

    Order placeOrder(Order order);
    public Order retrieveOrder();
    public void updateOrder();
    public void processPayment();
}
