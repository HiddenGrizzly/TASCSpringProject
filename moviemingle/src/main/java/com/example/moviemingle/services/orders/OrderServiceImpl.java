package com.example.moviemingle.services.orders;

import com.example.moviemingle.dtos.order.OrderDTO;
import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.dtos.order.OrderUpdateStatusDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.entities.OrderStatus;
import com.example.moviemingle.exceptions.OrderNotFoundException;
import com.example.moviemingle.mappers.OrderDetailMapper;
import com.example.moviemingle.mappers.OrderMapper;
import com.example.moviemingle.repositories.OrderDetailRepository;
import com.example.moviemingle.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public List<OrderDTO> getAllOrder(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable).stream().peek(order -> order.setTotalPrice()).map(orderMapper::orderToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
        order.setTotalPrice();
        return orderMapper.orderToOrderDTO(order);
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_Id(orderId);
        return orderDetails.stream()
                .map(orderDetail -> orderDetailMapper.orderDetailToOrderDetailDTO(orderDetail))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByUsername(String username) {
        return orderRepository.findByUser_Username(username).stream()
                .peek(order -> order.setTotalPrice())
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUser_Id(userId);
        return orders.stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<OrderDTO> getAllOrdersInMonth(int month) {
        return orderRepository.getAllOrdersInMonth(month).stream()
                .peek(order -> order.setTotalPrice())
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Order createOrder(OrderDTO orderDto) {
        Order order = orderMapper.orderDTOToOrder(orderDto);
        return orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
//        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }
    @Override
    public void updateOrderStatus(Long orderId, OrderUpdateStatusDTO dto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
        order.setOrderStatus(dto.getOrderStatus());
        orderRepository.save(order);
    }
    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}
