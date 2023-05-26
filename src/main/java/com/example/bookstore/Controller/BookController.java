package com.example.bookstore.Controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class BookController {
    private BookServiceImpl bookServiceImpl;

    @Autowired
    public void BookServiceImpl(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    // list all books
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookServiceImpl.getAllBooks();
    }

    // list book based on keywords
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("keyword") String keyword){
        return bookServiceImpl.searchBook(keyword);
    }

    // search book by id
    @GetMapping("/books/{id}")
    public Book searchById(@PathVariable int id){
        return bookServiceImpl.searchById(id);
    }

    // add book passing book object in body
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){
        return bookServiceImpl.addBook(book);
    }

    // delete book based on id
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id){
        return bookServiceImpl.deleteBookById(id);
    }

}
