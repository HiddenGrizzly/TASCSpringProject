package com.example.moviemingle.repositories;

import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List <OrderDetail> findByOrder_Id(Long orderId);
}
