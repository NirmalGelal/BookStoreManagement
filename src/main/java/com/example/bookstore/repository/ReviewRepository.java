package com.example.bookstore.repository;

import com.example.bookstore.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    public Review save(Review review);
    public Review findById(int id);

    @Query("SELECT r from Review r where r.book.id=:id")
    public List<Review> findByBookId(int id);

}
