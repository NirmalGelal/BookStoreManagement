package com.example.bookstore.repository;


import com.example.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order save (Order order);
    Order deleteById(int id);
    Order searchById (int id);

    @Query("from Order o")
    List<Order> getOrders();

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.id = :id")
    void update(@Param("id") int id, @Param("status") String status);

    @Modifying
    @Transactional
    @Query("DELETE Order o where o.user.id=:userId")
    void deleteByUserId(int userId);

    @Query("SELECT o from Order o where o.user.id =:userId")
    Order findByUserId(int userId);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.user = :#{#newOrder.user}, o.books = :#{#newOrder.books}, o.totalAmount = :#{#newOrder.totalAmount}, o.status = :#{#newOrder.status} WHERE o.id = :#{#newOrder.id}")
    void updateOrderById( Order newOrder);

}
