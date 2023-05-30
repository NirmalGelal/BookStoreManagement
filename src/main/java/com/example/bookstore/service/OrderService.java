package com.example.bookstore.service;

import com.example.bookstore.dto.request.OrderRequestDto;
import com.example.bookstore.dto.request.PaymentRequestDto;
import com.example.bookstore.dto.request.UpdateOrderRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Order;


public interface OrderService {
//    retrieving orders, updating order status, and processing payments
    public Response placeOrder(OrderRequestDto orderRequestDto);
    public Response<Order> updateOrder(UpdateOrderRequestDto updateOrderRequestDto);
    public Response getAllOrders();
    public Response processPayment(PaymentRequestDto paymentRequestDto);
}
