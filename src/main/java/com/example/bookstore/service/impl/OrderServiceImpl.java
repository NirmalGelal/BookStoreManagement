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
    private BookServiceImpl bookServiceImpl;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, BookRepository bookRepository, ShoppingCartServiceImpl shoppingCartServiceImpl, BookServiceImpl bookServiceImpl) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.shoppingCartServiceImpl = shoppingCartServiceImpl;
        this.bookServiceImpl = bookServiceImpl;

    }

    @Override
    public Response placeOrder(OrderRequestDto orderRequestDto, int userId) {
        Response response = new Response();
        List<Book> bookList = new ArrayList<>() {
        };
        double price = 0;
        for(Integer book_id: orderRequestDto.getBookIds()){
            if(!bookRepository.findById(book_id).isPresent()){
                response.setMessage("Invalid bookId found");
                return response;
            }
            else if (bookRepository.findById(book_id).get().getAvailability()<=0){
                response.setMessage("No book available (stock out)");
                return response;
            }
            bookList.add(bookRepository.searchById(book_id));
            price += bookRepository.searchById(book_id).getPrice();
        }
        User user = userRepository.findById(userId);

        Order order = new Order();
        order.setUser(user);
        order.setBooks(bookList);
        order.setTotalAmount(price);
        order.setStatus("ordered");
        orderRepository.save(order);

        for(Integer bookId: orderRequestDto.getBookIds()){
            int updatedQuantity = bookRepository.findById(bookId).get().getAvailability() - 1;
            bookServiceImpl.updateBookAvailability(bookId,updatedQuantity);
        }

        response.setMessage("order placed");
        ArrayList<String> message = new ArrayList<>();
        for (int bookId : orderRequestDto.getBookIds()){
            message.add(bookRepository.findById(bookId).get().getTitle());
        }
        response.setData(message);
        return response;
    }
    @Override
    @Transactional
    public Response<Optional<Order>> processPayment(PaymentRequestDto paymentRequestDto, int userId) {
        Response<Optional<Order>> response = new Response();

        if (orderRepository.findById(paymentRequestDto.getOrderId()).isPresent()){
            int userIdAtDb = orderRepository.findById(paymentRequestDto.getOrderId()).get().getUser().getId();
            if(userIdAtDb==userId) {
                orderRepository.update(paymentRequestDto.getOrderId(), "paid");
                response.setMessage("payment done using credit card: " + paymentRequestDto.getCreditCardNo() + " for order_id: " + paymentRequestDto.getOrderId());
                return response;
            }
        }
        response.setMessage("Invalid orderId");
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
        Response<List<Order>> response1 = response;
        return response1;
    }

    @Override
    @Transactional
    public Response updateOrder(UpdateOrderRequestDto updateOrderRequestDto, int userId) {
        Response<String> response = new Response<>();

        int orderId = updateOrderRequestDto.getOrderId();
        Optional<Order> orderOpt = orderRepository.findById(orderId);

        if(orderOpt.isPresent()){
            if(orderOpt.get().getUser().getId()==userId) {
                Order order = orderOpt.get();
                List<Book> oldBooks = order.getBooks();
                for (Book book1 : oldBooks) {
                    book1.setAvailability(book1.getAvailability()+1);
                    bookRepository.save(book1);
//                    bookRepository.updateById(book1.getId(), book1.getAvailability() + 1);
                }
                List<Book> newBooks = new ArrayList<>();
                for (int bookId : updateOrderRequestDto.getBookIds()) {
                    Optional<Book> book = bookRepository.findById(bookId);
                    if (book.get().getAvailability() <= 0) {
                        response.setMessage("No book available (stock out)");
                        return response;
                    }
                    bookRepository.updateById(bookId, book.get().getAvailability() - 1);
                    newBooks.add(book.get());
                }
                order.setUser(userRepository.findById(userId));
                order.setBooks(newBooks);
                order.setTotalAmount(shoppingCartServiceImpl.calculateTotalAmount(newBooks));
                order.setStatus("ordered");

                orderRepository.save(order);

                for (int bookId : updateOrderRequestDto.getBookIds()) {
                    Optional<Book> book = bookRepository.findById(bookId);
                    int newAvailability = book.get().getAvailability() - 1;
                    bookRepository.updateById(bookId, newAvailability);
                }

                response.setMessage("Order updated successfully");
                return response;
            }
        }
        response.setMessage("Invalid orderId.");
        return response;



    }
    @Override
    @Transactional
    public Response cancelOrder(int userId){

        Order order = orderRepository.findByUserId(userId);
        List<Book> books = order.getBooks();

        // increase the stock of a book if order is cancelled
        for(Book book: books){
            int newAvailability = book.getAvailability() + 1;
            bookRepository.updateById(book.getId(), newAvailability);
        }
        orderRepository.deleteById(order.getId());

        Response<String> response = new Response<>();
        response.setMessage("Order deleted of user : "+ userId);
        return response;

    }



}
