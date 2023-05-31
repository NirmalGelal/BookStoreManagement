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
    public Response placeOrder(@RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request){
        Response response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }

        else if(request.getSession().getAttribute("userRole").equals("user")){
            return orderServiceImpl.placeOrder(orderRequestDto,(Integer) request.getSession().getAttribute("userId"));
        }
        response.setMessage("Admin cannot place an order");
        return  response;
    }

    @PostMapping("/checkout")
    public Response checkout(@RequestBody PaymentRequestDto paymentRequestDto, HttpServletRequest request){
        Response response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        return orderServiceImpl.processPayment(paymentRequestDto,(Integer)request.getSession().getAttribute("userId"));
    }

    @GetMapping("/orders")
    public Response getAllOrders(HttpServletRequest request){
        Response response = new Response<>();

        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;

        }
        else if(request.getSession().getAttribute("userRole").equals("admin")){
            return orderServiceImpl.getAllOrders();
        }
        response.setMessage("User not authorized to view orders.");
        return response;
    }

    @PutMapping("/update-order")
    public Response<Order> updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequestDto, HttpServletRequest request){
        Response response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        else if(request.getSession().getAttribute("userRole").equals("user")){
            return orderServiceImpl.updateOrder(updateOrderRequestDto,(Integer) request.getSession().getAttribute("userId")
            );
        }
        response.setMessage("User not authorized to update orders.");
        return response;
    }

    @PostMapping("/cancel-order")
    public Response<String> cancelOrder(HttpServletRequest request){
        Response response = new Response<>();
        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }
        else if (request.getSession().getAttribute("userRole").equals("user")){
            return orderServiceImpl.cancelOrder((Integer) request.getSession().getAttribute("userId"));
        }
        response.setMessage("User not authorized to cancel orders.");
        return response;
    }
}
