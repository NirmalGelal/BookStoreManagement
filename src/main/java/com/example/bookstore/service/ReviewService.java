package com.example.bookstore.service;

import com.example.bookstore.dto.request.ReviewRequestDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Review;


public interface ReviewService {
    public Response addReview(ReviewRequestDto reviewRequestDto);
    public Response retrieveReview(int bookId);
    public Response calculateAverageRating(int bookId);
}
