package com.example.moviemingle.repositories;

import com.example.moviemingle.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Username(String username);
    List<Order> findByUser_Id(Long userId);
    @Query("SELECT o FROM Order o WHERE MONTH(o.createdAt) = :month")
    List<Order> getAllOrdersInMonth(int month);

}
