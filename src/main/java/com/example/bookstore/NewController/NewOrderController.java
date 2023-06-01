package com.example.bookstore.NewController;

import com.example.bookstore.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewOrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @GetMapping("/orders")
    public String getAllOrders(Model model){
        model.addAttribute("orders",orderServiceImpl.getAllOrders().getData());
        return "orderList";
    }
}
