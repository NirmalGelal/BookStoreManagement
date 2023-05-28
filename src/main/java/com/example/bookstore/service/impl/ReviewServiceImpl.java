package com.example.bookstore.service.impl;

import com.example.bookstore.dto.request.ReviewRequestDto;
import com.example.bookstore.dto.response.AverageReviewResponseDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Review;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ReviewRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService
{
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Response<Review> addReview(ReviewRequestDto reviewRequestDto) {
        Response<Review> response = new Response<>();

        int bookId = reviewRequestDto.getBookId();
        int userId = reviewRequestDto.getUserId();
        String message = reviewRequestDto.getReviewMessage();
        int rating = reviewRequestDto.getRating();

        User user = userRepository.findById(userId);
        Book book = bookRepository.searchById(bookId);

        Review review = new Review();
        review.setBook(book);
        review.setUser(user);
        review.setComment(message);
        review.setRating(rating);

        Review review1 = reviewRepository.save(review);
        response.setData(review1);
        response.setMessage("Review Added Successfully");
        String whatIsMessage = response.getMessage();

        return response;
    }

    @Override
    public Response retrieveReview(int bookId) {
        Response<String> response = new Response<>();
        Review review = reviewRepository.findById(bookId);
        String reviewComment = review.getComment();
        response.setData(reviewComment);
        response.setMessage("Review retrieved successfully");
        return response;
    }

    @Override
    public Response<AverageReviewResponseDto> calculateAverageRating(int bookId) {
        AverageReviewResponseDto averageReviewResponseDto = new AverageReviewResponseDto();
        List<Review> reviews = reviewRepository.findByBookId(bookId);

        int ratingSum = 0;
        int counter = 0;

        for(Review review: reviews){
            ratingSum += review.getRating();
            counter++;
        }
        System.out.println(ratingSum);
        System.out.println(counter);
        float average = (float) ratingSum/counter;
        System.out.println(average);

        averageReviewResponseDto.setAverageReview(average);
        averageReviewResponseDto.setBookId(bookId);

        Response<AverageReviewResponseDto> response = new Response<>();
        response.setMessage("Average calculated successfully");
        response.setData(averageReviewResponseDto);
        return response;
    }
}
