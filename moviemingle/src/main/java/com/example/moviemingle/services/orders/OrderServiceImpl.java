package com.example.moviemingle.services.orders;

import com.example.moviemingle.dto.OrderDTO;
import com.example.moviemingle.dto.OrderDetailDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.exceptions.OrderNotFoundException;
import com.example.moviemingle.mappers.OrderDetailMapper;
import com.example.moviemingle.mappers.OrderMapper;
import com.example.moviemingle.repositories.OrderDetailRepository;
import com.example.moviemingle.repositories.OrderRepository;
//import com.example.moviemingle.services.orderdetails.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        return orderRepository.findAll(pageable).stream().peek(order -> order.setTotalPrice()).
                map(orderMapper::orderToOrderDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
        order.setTotalPrice();
        return orderMapper.orderToOrderDTO(order);
    }

    @Override
    public List<OrderDTO> getOrderByUsername(String username) {
        return orderRepository.findByUser_Username(username).stream()
                .peek(order -> order.setTotalPrice())
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void createOrder(OrderDTO orderDto) {
        Order order = orderMapper.orderDTOToOrder(orderDto);
        orderRepository.save(order);
    }

//    @Override
//    public OrderDTO addOrderDetail(Long orderId, OrderDetailDTO orderDetailDTO) {
//        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
//        OrderDetail orderDetail = orderDetailMapper.orderDetailDTOToOrderDetail(orderDetailDTO);
//        orderDetail.setOrder(order);
//        if (order.getOrderDetails() == null) {
//            order.setOrderDetails(new HashSet<>());
//        }
//        order.getOrderDetails().add(orderDetail);
//        orderDetailRepository.save(orderDetail);
//        order.setTotalPrice();
//        orderRepository.save(order);
//        return orderMapper.orderToOrderDTO(order);
//    }

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
