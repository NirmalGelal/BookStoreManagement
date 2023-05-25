package com.example.bookstore.service.impl;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public String addBook(Book book) {

        return "Book Added Successfully";
    }

    @Override
    public List<Book> searchBook(String keyword) {
        return null;
    }

    @Override
    public String updateBookAvailability(Book book, int quantity) {
        return null;
    }
}
