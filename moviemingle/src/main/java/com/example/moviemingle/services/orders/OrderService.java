package com.example.moviemingle.services.orders;

import com.example.moviemingle.dtos.order.OrderDTO;
import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.dtos.order.OrderUpdateStatusDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrder(int page, int size);
    OrderDTO getOrderById(Long orderId);
    List<OrderDetailDTO> getOrderDetailByOrderId(Long orderId);
    List<OrderDTO> getOrderByUsername(String username);
    List<OrderDTO> getOrderByUserId(Long userId);
    List<OrderDTO> getAllOrdersInMonth(int month);
    Order createOrder(OrderDTO orderDto);
    void updateOrder(Long orderId, OrderDTO orderDto);
    void updateOrderStatus(Long orderId, OrderUpdateStatusDTO dto);
    void deleteOrder(Long orderId);
}
