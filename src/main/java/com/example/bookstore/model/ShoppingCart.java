package com.example.bookstore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private  int id;
    @OneToOne (fetch = FetchType.LAZY)
    private User user;
    @OneToMany (fetch = FetchType.LAZY)
    private List<Book> books;
    @Column(name = "TotalAmount")
    private double totalAmount;

    public ShoppingCart() {
    }

    public ShoppingCart(int id, User user, List<Book> books, double totalAmount) {
        this.id = id;
        this.user = user;
        this.books = books;
        this.totalAmount = totalAmount;
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String addBook(Book book){
        books.add(book);
        return "Book added to cart successfully";
    }
    public String removeBook (Book book){
        if(!books.isEmpty()) {
            books.remove(book);
            return "Book removed from cart successfully";
        }
        else return "Cart is empty";
    }
    public String clearCart(){
        if(!books.isEmpty()){
            books.clear();
            return "Cart cleared successfully";
        }
        else return "Cart is empty";
    }
    public double calculateTotalAmount(){
        double totalAmount = 0;
        for (Book book:books
             ) {
            totalAmount += book.getPrice();
        }
        return totalAmount;
    }

}
