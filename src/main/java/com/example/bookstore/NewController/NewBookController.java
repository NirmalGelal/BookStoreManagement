package com.example.bookstore.NewController;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NewBookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("/books")
    public String getAllBooks(Model model, @RequestParam(defaultValue = "1") int  page , @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy){
        model.addAttribute("allBooks",bookServiceImpl.getAllBooks(page-1,size,sortBy).getData());
        return "bookList";
    }

    @DeleteMapping("/delete-book/{id}")
    public String deleteBookById(@PathVariable int id){
        bookServiceImpl.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/add-book-form")
    public String addBookForm(Model model){
        model.addAttribute("book",new Book());
        return "addBookForm";
    }

    @PostMapping("/add-book")
    public String addBooks(Model model,@ModelAttribute Book book){
        bookServiceImpl.addNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("/update-book-form/{id}")
    public String updateBookForm(Model model, @PathVariable int id){
        Book book = bookServiceImpl.searchById(id).getData();
        model.addAttribute("book",book);
        return "updateBookForm";
    }

    @PutMapping("/update-book")
    public String updateBook(Model model, @ModelAttribute Book book){
        bookServiceImpl.updateBook(book);
//        model.addAttribute("book", book);5
        return "redirect:/books";
    }
}
