package com.example.bookstore.NewController;

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

//    @PutMapping("update-book/{id}")
//    public String updateBookById(@PathVariable int id){
//
//    }

    @GetMapping("/update")
    public String updateBooks(){
        return "updateBook";
    }
}
