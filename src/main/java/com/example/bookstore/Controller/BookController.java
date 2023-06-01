package com.example.bookstore.Controller;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.boot.model.internal.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public Response getAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy){
        return bookServiceImpl.getAllBooks(page, size, sortBy);
    }

    // list book based on keywords
    @GetMapping("/search")
    public Response searchBooks(@RequestParam("keyword") String keyword){
        return bookServiceImpl.searchBook(keyword);
    }

    // search book by id
    @GetMapping("/books/{id}")
    public Response searchById(@PathVariable int id){
        return bookServiceImpl.searchById(id);
    }

    // add book passing book object in body
    @PostMapping("/add")
    public Response addBook(@RequestBody Book book, HttpServletRequest request){
        Response response = new Response<>();

        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;
        }

        else if (request.getSession().getAttribute("userRole").equals("admin")){
            return bookServiceImpl.addNewBook(book);
        }
        response.setMessage("User not authorized to add book.");
        return response;
    }

    @PutMapping("/update-stock")
    public Response updateStock(@RequestParam int bookId, int availability, HttpServletRequest request){
        Response response = new Response<>();

        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;

        }
        else if (request.getSession().getAttribute("userRole").equals("admin")){
            return bookServiceImpl.updateBookAvailability(bookId, availability);
        }
        response.setMessage("User not authorized to update stocks.");
        return response;
    }


    // delete book based on id
    @DeleteMapping("/delete/{bookId}")

    public Response deleteBook(@PathVariable int bookId, HttpServletRequest request){
        Response response = new Response<>();

        if(request.getSession().getAttribute("userRole")==null){
            response.setMessage("You need to login first.");
            return response;

        }
        else if (request.getSession().getAttribute("userRole").equals("admin")){
            return bookServiceImpl.deleteBookById(bookId);
        }
        response.setMessage("User not authorized to delete a book.");
        return response;
    }

}
