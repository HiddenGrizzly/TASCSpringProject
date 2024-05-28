package com.example.moviemingle.services.orders;

import com.example.moviemingle.dto.OrderDTO;
import com.example.moviemingle.dto.OrderDetailDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;

import java.util.List;
import java.util.Set;

public interface OrderService {
    List<OrderDTO> getAllOrder(int page, int size);
    OrderDTO getOrderById(Long orderId);
    List<OrderDTO> getOrderByUsername(String username);
    void createOrder(OrderDTO orderDto);
//    OrderDTO addOrderDetail(Long orderId, OrderDetailDTO orderDetailDTO);
    void updateOrder(Long orderId, OrderDTO orderDto);
    void deleteOrder(Long orderId);
}
