package com.example.bookstore.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "Order")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @ManyToOne (fetch = FetchType.LAZY)
    private User user;
    @OneToMany (fetch = FetchType.LAZY)
    private List<Book> books;
    @Column(name = "TotalAmount")
    private double totalAmount;
    @Column(name = "Status")
    private String status;
    public Order() {
    }
    public Order(int id, User user, List<Book> books, double totalAmount, String status) {
        this.id = id;
        this.user = user;
        this.books = books;
        this.totalAmount = totalAmount;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
