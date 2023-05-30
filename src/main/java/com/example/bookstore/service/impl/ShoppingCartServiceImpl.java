package com.example.bookstore.service.impl;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.ShoppingCart;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ShoppingCartRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService
{
    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;
    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, BookRepository bookRepository){
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    @Override
    public Response<ShoppingCart> addToCart(List<Integer> bookIds, int userId) {

        List<Book> books = new ArrayList<>();
        for(int book_id:bookIds){
            Book book = bookRepository.searchById(book_id);
            books.add(book);
        }
        double totalAmount = calculateTotalAmount(books);
        User user = userRepository.findById(userId);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setBooks(books);
        shoppingCart.setUser(user);
        shoppingCart.setTotalAmount(totalAmount);

        ShoppingCart cart = shoppingCartRepository.save(shoppingCart);

        Response<ShoppingCart> response = new Response<>();
        response.setMessage("Added to ShoppingCart");
        response.setData(cart);
        return response;

    }

    @Override
    public Response<ShoppingCart> removeFromCart(int shoppingCartId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        Response<ShoppingCart> response = new Response<>();
        if(shoppingCart.isPresent()){
            response.setMessage("Removed successfully from cart");
            shoppingCartRepository.deleteById(shoppingCartId);
            response.setMessage(String.format("Shopping cart with ID: %d successfully deleted.",shoppingCartId));
            return response;
        }else{
            response.setMessage(String.format("Shopping cart with ID: %d does not exist.",shoppingCartId));
        }

        return response;

    }

    @Override
    public double calculateTotalAmount(List<Book> books) {
        double totalAmount = 0;
        for(Book book:books){
            totalAmount += book.getPrice();
        }
        return totalAmount;
    }

}
