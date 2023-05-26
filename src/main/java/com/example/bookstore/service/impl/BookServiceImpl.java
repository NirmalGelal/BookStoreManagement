package com.example.bookstore.service.impl;

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
    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Override
    public String deleteBookById(int id) {
        bookRepository.deleteBookById(id);
        return "Book deleted successfully";
    }
    @Override
    public List<Book> searchBook(String keyword) {
        return bookRepository.searchBook(keyword);
    }
    @Override
    public Book searchById(int id) {
        return bookRepository.searchById(id);
    }



    @Override
    public String updateBookAvailability(Book book, int quantity) {
        return null;
    }


}
