package com.example.bookstore.service;

import com.example.bookstore.dto.request.OrderRequestDto;
import com.example.bookstore.dto.request.PaymentRequestDto;
import com.example.bookstore.dto.request.UpdateOrderRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Order;


public interface OrderService {
    public Response placeOrder(OrderRequestDto orderRequestDto, int userId);
    public Response<Order> updateOrder(UpdateOrderRequestDto updateOrderRequestDto, int userId);
    public Response getAllOrders(int page, int size, String sortBy);
    public Response processPayment(PaymentRequestDto paymentRequestDto, int userId);
    public Order getOrderById(int orderId);
    public Response cancelOrder(int userId);
    public Order save(Order order);
}
