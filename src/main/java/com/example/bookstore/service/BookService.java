package com.example.bookstore.service;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;

import java.util.List;

public interface BookService {
    public Response addBook(Book book);
    public Response getAllBooks();
    public Response searchBook(String keyword);
    public Response searchById(int id);
    public Response updateBookAvailability(Book book, int quantity);
    public Response deleteBookById(int id);

}
