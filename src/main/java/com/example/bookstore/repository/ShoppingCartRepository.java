package com.example.bookstore.repository;

import com.example.bookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    public ShoppingCart save(ShoppingCart shoppingCart);
    public ShoppingCart deleteById(int id);
    @Query("SELECT s from ShoppingCart s where s.user.id=:userId")
    public ShoppingCart findByUserId(int userId);

}
