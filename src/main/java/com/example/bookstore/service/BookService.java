package com.example.bookstore.service;

import com.example.bookstore.model.Book;

import java.util.List;

public interface BookService {
    public Book addBook(Book book);
    public List<Book> getAllBooks();
    public List<Book> searchBook(String keyword);

    Book searchById(int id);

    public String updateBookAvailability(Book book, int quantity);
    public String deleteBookById(int id);

}
