package com.example.bookstore.Controller;

import com.example.bookstore.dto.request.OrderRequestDto;
import com.example.bookstore.dto.request.PaymentRequestDto;
import com.example.bookstore.dto.request.UpdateOrderRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Order;
import com.example.bookstore.service.impl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping("/place-order")
    public Response placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderServiceImpl.placeOrder(orderRequestDto);
    }

    @PostMapping("/checkout")
    public Response checkout(@RequestBody PaymentRequestDto paymentRequestDto){
        return orderServiceImpl.processPayment(paymentRequestDto);
    }

    @GetMapping("/orders")
    public Response getAllOrders(){
        return orderServiceImpl.getAllOrders();
    }

    @PutMapping("/update-order")
    public Response<Order> updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequestDto){
        System.out.println("Controller reached");
        return orderServiceImpl.updateOrder(updateOrderRequestDto);
    }
}
