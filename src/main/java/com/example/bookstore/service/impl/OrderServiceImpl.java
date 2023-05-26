package com.example.bookstore.service.impl;

import com.example.bookstore.dto.request.OrderRequestDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.ShoppingCartRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

//    @Override
//    public Order placeOrder(OrderRequestDto orderRequestDto) {
//        Book book = bookRepository.searchById(orderRequestDto.getBook_id());
//        User user = userRepository.findById(orderRequestDto.getUser_id());
////        Order order = new Order();
//        orderRepository.save(order);
//        return null;
//    }
        @Override
        public Order placeOrder(Order order) {
//            Book book = bookRepository.searchById(orderRequestDto.getBook_id());
//            User user = userRepository.findById(orderRequestDto.getUser_id());
    //        Order order = new Order();
            orderRepository.save(order);
            return null;
        }


    @Override
    public Order retrieveOrder() {
        return null;
    }

    @Override
    public void updateOrder() {

    }

    @Override
    public void processPayment() {

    }
}
