package com.example.moviemingle.services.orderdetail;

import com.example.moviemingle.dtos.order.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDTO> getAllOrderDetail(int page, int size);
    OrderDetailDTO getOrderDetailById(Long orderDetailId);
    void addOrderDetail(OrderDetailDTO orderDetailDTO);
    void updateOrderDetail(Long orderDetailId, OrderDetailDTO orderDetailDTO);
    void deleteOrderDetail(Long orderDetailId);
}
