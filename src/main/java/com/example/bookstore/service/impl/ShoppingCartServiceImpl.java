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
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Response<ShoppingCart> addToCart(int bookId, int userId) {

        Response<ShoppingCart> response = new Response<>();

        if (shoppingCartRepository.findByUserId(userId) != null) {
            ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);

            List<Book> books = shoppingCart.getBooks();
            Book book = bookRepository.findById(bookId).get();
            books.add(book);

            double totalAmount = calculateTotalAmount(books);
            shoppingCart.setTotalAmount(totalAmount);
            shoppingCart.setBooks(books);
            shoppingCartRepository.save(shoppingCart);
            response.setMessage("added to shopping cart");
            response.setData(shoppingCart);
            return response;
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        ArrayList<Book> books = new ArrayList<>();
        Book book = bookRepository.findById(bookId).get();
        books.add(book);
        double totalAmount = calculateTotalAmount(books);
        User user = userRepository.findById(userId);
        shoppingCart.setUser(user);
        shoppingCart.setBooks(books);
        shoppingCart.setTotalAmount(totalAmount);
        shoppingCartRepository.save(shoppingCart);
        response.setMessage("added to shopping cart");
        response.setData(shoppingCart);
        return response;
    }

    @Override
    public Response<ShoppingCart> removeFromCart(int bookId, int userId) {

        Response<ShoppingCart> response = new Response<>();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        List<Book> books = shoppingCart.getBooks();
        List<Integer> bookIds = new ArrayList<>();
        for (Book b : books) {
            bookIds.add(b.getId());

        }
        for (int i = 0; i < books.size() ; i++) {
            if(books.get(i).getId()==bookId){
                books.remove(books.get(i));
            }
        }

        if (bookIds.contains(bookId)) {
//            books.remove(bookRepository.findById(bookId));
            shoppingCart.setBooks(books);
            shoppingCartRepository.save(shoppingCart);
            response.setMessage("Book successfully removed from cart");
            return response;
        }
        response.setMessage("Invalid bookId");
        response.setData(null);
        return response;

    }

    @Override
    public Response clearCart(int userId) {
        Response response = new Response<>();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        shoppingCartRepository.delete(shoppingCart);
        response.setMessage("Cart cleared successfully");
        return response;
    }

    @Override
    public double calculateTotalAmount(List<Book> books) {
        double totalAmount = 0;
        for (Book book : books) {
            totalAmount += book.getPrice();
        }
        return totalAmount;
    }

}
