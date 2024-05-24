package com.example.moviemingle.services.orders;

import com.example.moviemingle.dto.OrderDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.exceptions.OrderNotFoundException;
import com.example.moviemingle.mappers.OrderMapper;
import com.example.moviemingle.repositories.OrderRepository;
import com.example.moviemingle.services.orderdetails.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderDTO> getAllOrder() {
        return orderRepository.findAll().stream().map(orderMapper::orderToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
        return orderMapper.orderToOrderDTO(order);
    }
    @Override
    public void addOrder(OrderDTO orderDto) {
        Order order = orderMapper.orderDTOToOrder(orderDto);
        orderRepository.save(order);
    }
    @Override
    public void updateOrder(Long orderId, OrderDTO orderDto) {
        Order existingOrder = orderRepository.findById(orderId).get();
        if (existingOrder != null) {
            existingOrder = orderMapper.orderDTOToOrder(orderDto);
            orderRepository.save(existingOrder);
        }
    }
    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
