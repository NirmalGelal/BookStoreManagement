package com.example.bookstore.Controller;

import com.example.bookstore.dto.request.ReviewRequestDto;
import com.example.bookstore.dto.response.AverageReviewResponseDto;
import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.Review;
import com.example.bookstore.service.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private ReviewServiceImpl reviewServiceImpl;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }

    @PostMapping("/add-review")
    public Response<Review> addReview(@RequestBody ReviewRequestDto reviewRequestDto){
        return reviewServiceImpl.addReview(reviewRequestDto);
    }

    @GetMapping("/reviews/{bookId}")
    public Response<String> retrieveReview (@PathVariable int bookId){
        return reviewServiceImpl.retrieveReview(bookId);
    }

    @GetMapping("/average-review/{bookId}")
    public Response<AverageReviewResponseDto> calculateAverageRating(@PathVariable int bookId){
        return reviewServiceImpl.calculateAverageRating(bookId);
    }
}
