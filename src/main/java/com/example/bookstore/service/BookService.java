package com.example.bookstore.service;

import com.example.bookstore.model.Book;

import java.util.List;

public interface BookService {
    public String addBook(Book book);
    public List<Book> searchBook(String keyword);
    public String updateBookAvailability(Book book, int quantity);
}
