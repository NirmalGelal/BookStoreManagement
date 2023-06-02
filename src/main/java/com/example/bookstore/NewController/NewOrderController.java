package com.example.bookstore.NewController;

import com.example.bookstore.model.Order;
import com.example.bookstore.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NewOrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @GetMapping("/orders")
    public String getAllOrders(Model model, @RequestParam(defaultValue = "1") int  page , @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy){
        model.addAttribute("orders",orderServiceImpl.getAllOrders(page-1, size, sortBy).getData());
        return "orderList";
    }

    @GetMapping("/update-order-form/{id}")
    public String updateOrderForm(Model model, @PathVariable int id){
        Order order = orderServiceImpl.getOrderById(id);
        model.addAttribute("order", order);
        return "updateOrderStatusForm";
    }

    @PutMapping("/update-order")
    public String updateOrder(Model model, @ModelAttribute Order order){
        orderServiceImpl.save(order);
        return "redirect:/orders";
    }
}
