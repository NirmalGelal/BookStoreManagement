package com.example.bookstore.service.impl;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public Response addNewBook(Book book) {

        Response<Book> response = new Response<>();
        response.setMessage("Book added successfully");

        Book addedBook = bookRepository.save(book);
        response.setData(addedBook);
        return response;
    }
    @Override
    public Response getAllBooks(int page, int size, String sortBy) {

        Response<Page<Book>> response = new Response<>();
        Page<Book> bookList =  bookRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
        response.setMessage("Books retrieved successfully");
        response.setData(bookList);
        return response;
    }
    @Override
    public Response deleteBookById(int bookId) {
        Response response = new Response<>();
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent() && book.get().getAvailability()>=1) {
            int newAvailability = book.get().getAvailability() - 1;
            bookRepository.updateAvailabilityById(bookId, newAvailability);
            response.setMessage("Book deleted successfully");
            response.setData("New stock updated");
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
    public Response<Book> searchById(int id) {
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
    public Response updateBookAvailability(int bookId, int quantity) {
        Response response = new Response();
        bookRepository.updateAvailabilityById(bookId, quantity);
        int newStock = bookRepository.searchById(bookId).getAvailability();
        response.setData("New stock quantity of bookId "+ bookId+" is "+ newStock );
        response.setMessage("Availability updated.");
        return response;
    }

    @Override
    public String updateBook( Book book){
        bookRepository.save(book);
        return "";
    }


}
