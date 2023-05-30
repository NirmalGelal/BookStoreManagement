package com.example.bookstore.service.impl;
import com.example.bookstore.dto.request.OrderRequestDto;
import com.example.bookstore.dto.request.PaymentRequestDto;
import com.example.bookstore.dto.request.UpdateOrderRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.ShoppingCartRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private ShoppingCartServiceImpl shoppingCartServiceImpl;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, BookRepository bookRepository, ShoppingCartServiceImpl shoppingCartServiceImpl) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;

    }

    @Override
    public Response placeOrder(OrderRequestDto orderRequestDto) {
        Response response = new Response();
        List<Book> bookList = new ArrayList<>() {
        };
        double price = 0;
        for(Integer book_id: orderRequestDto.getBookIds()){
            bookList.add(bookRepository.searchById(book_id));
            price += bookRepository.searchById(book_id).getPrice();
        }
        User user = userRepository.findById(orderRequestDto.getUserId());

        Order order = new Order();
        order.setUser(user);
        order.setBooks(bookList);
        order.setTotalAmount(price);
        order.setStatus("ordered");
        orderRepository.save(order);

        response.setMessage("order placed");
        response.setData("");
        return response;
    }
    @Override
    @Transactional
    public Response<Order> processPayment(PaymentRequestDto paymentRequestDto) {

        Response<Order> response = new Response();
        orderRepository.update(paymentRequestDto.getOrderId(),"paid");
        response.setMessage("payment done using credit card: "+paymentRequestDto.getCreditCardNo()+" for order_id: "+paymentRequestDto.getOrderId());
        Optional<Order> order = orderRepository.findById(paymentRequestDto.getOrderId());
        log.info(" -- >:{}",order);
        log.info(" -- >:{}",paymentRequestDto.getOrderId());
        order.ifPresent(response::setData);
        return response;
    }

    public Response getAllOrders(){
        Response<List<Order>> response = new Response();
        List<Order> orders = orderRepository.getOrders();
        if(!orders.isEmpty()){
            response.setMessage("Orders retrieved successfully");
            response.setData(orderRepository.getOrders());
            return response;
        }
        response.setMessage("No orders found");
        response.setData(null);
        return response;
    }

    @Override
    @Transactional
    public Response updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
        List<Book> books = new ArrayList<>();
        for (int bookId:updateOrderRequestDto.getBookIds()){
            books.add(bookRepository.searchById(bookId));
        }
        User user = userRepository.findById(updateOrderRequestDto.getUserId());

        Order order = new Order();
        order.setBooks(books);
        order.setUser(user);
        order.setTotalAmount(shoppingCartServiceImpl.calculateTotalAmount(books));
        order.setStatus("ordered");

//        orderRepository.updateOrderById(updateOrderRequestDto.getOrderId(),order);

        orderRepository.deleteById(updateOrderRequestDto.getOrderId());
        orderRepository.save(order);
        Response response = new Response<>();
        response.setMessage("Order updated successfully");
        response.setData(order);
        return response;
    }


}
