package com.example.bookstore.Controller;

import com.example.bookstore.dto.request.OrderRequestDto;
import com.example.bookstore.model.Order;
import com.example.bookstore.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping("/place-order")
    public Order placeOrder(@RequestBody Order order){
        return orderServiceImpl.placeOrder(order);
    }
}
