package com.example.bookstore.service;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;

public interface BookService {
    public Response addNewBook(Book book);
//    public Response addStock(int availability);
    public Response getAllBooks();
    public Response searchBook(String keyword);
    public Response searchById(int id);
    public Response updateBookAvailability(int bookId, int quantity);
    public Response deleteBookById(int id);

}
