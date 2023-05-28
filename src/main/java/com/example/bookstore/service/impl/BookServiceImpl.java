package com.example.bookstore.service.impl;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Response addBook(Book book) {

        Response<Book> response = new Response<>();
        response.setMessage("Book added successfully");
        Book addedBook = bookRepository.save(book);
        response.setData(addedBook);
        return response;
    }
    @Override
    public Response getAllBooks() {

        Response<List<Book>> response = new Response<>();
        List<Book> bookList = bookRepository.findAll();
        response.setMessage("Books retrieved successfully");
        response.setData(bookList);
        return response;
    }
    @Override
    public Response deleteBookById(int id) {
        Response<Book> response = new Response<>();
        Book book = bookRepository.deleteBookById(id);
        if(book != null) {
            response.setMessage("Book deleted successfully");
            response.setData(book);
            return response;
        }
        response.setMessage("Invalid id");
        response.setData(null);
        return response;
    }
    @Override
    public Response searchBook(String keyword) {
        Response<List<Book>> response = new Response<>();
        List<Book> book = bookRepository.searchBook(keyword);
        if(book.isEmpty()) {
            response.setMessage("No book exist for this keyword");
            response.setData(null);
            return response;

        }
        response.setMessage("Books retrieved successfully");
        response.setData(book);
        return response;
    }
    @Override
    public Response searchById(int id) {
        Response<Book> response = new Response<>();
        Book book = bookRepository.searchById(id);
        if(book != null) {
            response.setMessage("Book retrieved successfully");
            response.setData(book);
            return response;
        }
        response.setMessage("Invalid id");
        response.setData(null);
        return response;
    }

    @Override
    public Response updateBookAvailability(Book book, int quantity) {
        return null;
    }


}
