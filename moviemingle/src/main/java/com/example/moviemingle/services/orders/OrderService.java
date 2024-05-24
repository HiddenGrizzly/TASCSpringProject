package com.example.moviemingle.services.orders;

import com.example.moviemingle.dto.OrderDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;

import java.util.List;
import java.util.Set;

public interface OrderService {
    List<OrderDTO> getAllOrder();
    OrderDTO getOrderById(Long orderId);
    void addOrder(OrderDTO orderDto);
    void updateOrder(Long orderId, OrderDTO orderDto);
    void deleteOrder(Long orderId);
}
