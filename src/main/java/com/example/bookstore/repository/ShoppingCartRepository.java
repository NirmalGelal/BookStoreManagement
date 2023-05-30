package com.example.bookstore.repository;

import com.example.bookstore.dto.response.Response;
import com.example.bookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    public ShoppingCart save(ShoppingCart shoppingCart);
    public ShoppingCart deleteById(int id);

//    @Query("select s from ShoppingCart s where s.id=:id")
//    public ShoppingCart searchById(int id);
}
