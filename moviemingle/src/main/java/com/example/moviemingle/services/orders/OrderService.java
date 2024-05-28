package com.example.moviemingle.services.orders;

import com.example.moviemingle.dtos.order.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrder(int page, int size);
    OrderDTO getOrderById(Long orderId);
    List<OrderDTO> getOrderByUsername(String username);
    void createOrder(OrderDTO orderDto);
//    OrderDTO addOrderDetail(Long orderId, OrderDetailDTO orderDetailDTO);
    void updateOrder(Long orderId, OrderDTO orderDto);
    void deleteOrder(Long orderId);
}
