package com.example.bookstore.service;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;



public interface ReviewService {
//    adding reviews, retrieving reviews for a book, and calculating average ratings
    public Response addReview(String comment);
    public Response retrieveReview(Book book);
    public Response calculateAverageRating(Book book);
}
