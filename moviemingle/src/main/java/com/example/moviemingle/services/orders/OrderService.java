package com.example.moviemingle.services.orders;

import com.example.moviemingle.dtos.order.OrderDTO;
import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.dtos.order.OrderUpdateStatusDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderStatus;
import com.example.moviemingle.entities.User;
import com.example.moviemingle.models.orders.OrderCreateReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
//    List<OrderDTO> getAllOrder(int page, int size);
    Page<OrderDTO> getAllOrder(Pageable pageable);
 
    OrderDTO getOrderById(Long orderId);
  
    List<OrderDetailDTO> getOrderDetailByOrderId(Long orderId);
  
    List<OrderDTO> getOrderByUsername(String username);
  
    List<OrderDTO> getOrderByUserId(Long userId);
  
    List<OrderDTO> getAllOrdersInMonth(int month);
 
    Order createOrder(OrderDTO orderDto);
    
    Order createUserOrder(User user, OrderCreateReq req);
  
    void updateOrderStatus(Long orderId, OrderUpdateStatusDTO dto);

    void deleteOrder(Long orderId);

    Order getById(Long id);
    
}
