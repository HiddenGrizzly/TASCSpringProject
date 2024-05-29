package com.example.moviemingle.services.orders;

import com.example.moviemingle.dtos.order.OrderDTO;
import com.example.moviemingle.dtos.order.OrderDetailDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrder(int page, int size);
    OrderDTO getOrderById(Long orderId);
    List<OrderDetailDTO> getOrderDetailByOrderId(Long orderId);
    List<OrderDTO> getOrderByUsername(String username);
    List<OrderDTO> getOrderByUserId(Long userId);
    List<OrderDTO> getAllOrdersInMonth(int month);
    void createOrder(OrderDTO orderDto);
//    OrderDTO addOrderDetail(Long orderId, OrderDetailDTO orderDetailDTO);
    void updateOrder(Long orderId, OrderDTO orderDto);
    void deleteOrder(Long orderId);
}
