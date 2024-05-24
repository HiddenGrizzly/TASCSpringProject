package com.example.moviemingle.repositories;

import com.example.moviemingle.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
