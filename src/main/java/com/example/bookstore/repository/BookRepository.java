package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAll();
    Book searchById(int id);
    @Query("select b from Book b where lower(b.author) like %:keyword% or lower(b.title) like %:keyword% or lower(b.genre) like %:keyword%")
    List<Book> searchBook(@Param("keyword") String keyword);
    Book save(Book book);


    int deleteBookById(int id);

    @Modifying
    @Query("UPDATE Book b set b.availability=:availability where b.id=:id")
    void updateById(int id, int availability);
}
