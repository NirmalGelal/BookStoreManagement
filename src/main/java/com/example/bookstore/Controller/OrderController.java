package com.example.bookstore.Controller;

import com.example.bookstore.dto.request.OrderRequestDto;
import com.example.bookstore.dto.request.PaymentRequestDto;
import com.example.bookstore.dto.request.UpdateOrderRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Order;
import com.example.bookstore.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response getAllOrders(HttpServletRequest request){
        if(request.getSession().getAttribute("userRole").equals("admin")){
            return orderServiceImpl.getAllOrders();
        }
        Response response = new Response<>();
        response.setMessage("User not authorized to view orders.");
        return response;
    }

    @PutMapping("/update-order")
    public Response<Order> updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequestDto, HttpServletRequest request){
        if(request.getSession().getAttribute("userRole").equals("admin")){
            return orderServiceImpl.updateOrder(updateOrderRequestDto);
        }
        Response response = new Response<>();
        response.setMessage("User not authorized to update orders.");
        return response;
    }
}
