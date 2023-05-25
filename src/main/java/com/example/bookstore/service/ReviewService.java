package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Review;

public interface ReviewService {
//    adding reviews, retrieving reviews for a book, and calculating average ratings
    public void addReview(String comment);
    public String retrieveReview(Book book);
    public int calculateAverageRating(Book book);
}
