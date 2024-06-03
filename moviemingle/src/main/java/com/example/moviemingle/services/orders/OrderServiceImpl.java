package com.example.moviemingle.services.orders;

import com.example.moviemingle.dtos.order.OrderDTO;
import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.dtos.order.OrderUpdateStatusDTO;
import com.example.moviemingle.entities.*;
import com.example.moviemingle.exceptions.NotfoundException;
import com.example.moviemingle.exceptions.OrderNotFoundException;
import com.example.moviemingle.mappers.OrderDetailMapper;
import com.example.moviemingle.mappers.OrderMapper;
import com.example.moviemingle.models.orders.OrderCreateReq;
import com.example.moviemingle.models.orders.OrderItem;
import com.example.moviemingle.repositories.MovieRepository;
import com.example.moviemingle.repositories.OrderDetailRepository;
import com.example.moviemingle.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    
    @Autowired
    private MovieRepository movieRepository;

    public Page<OrderDTO> getAllOrder(Pageable pageable) {
        Page<Order> ordersPage = orderRepository.findAll(pageable);
        return ordersPage.map(order -> {
            order.setTotalPrice();
            return orderMapper.orderToOrderDTO(order);
        });
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
        order.setOrderStatus(OrderStatus.OPEN.name());
        return orderRepository.save(order);
    }
    
    @Transactional
    @Override
    public Order createUserOrder(User user, OrderCreateReq req) {
        Order order = new Order();
        // set order status
        order.setOrderStatus(OrderStatus.OPEN.name());
        // set order user
        order.setUser(user);
        
        Payment payment = new Payment();
        payment.setPaymentMethod(req.getPaymentMethod().name());
        payment.setStatus(PaymentStatus.OPEN.ordinal());
        // set order payment
        order.setPayment(payment);
        Double total = 0.0;
        
        Set<OrderDetail> orderDetails = new HashSet<>();
        
        for (OrderItem item : req.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            Movie movie = movieRepository.findById(item.getMovieId()).orElseThrow(() -> new NotfoundException("Movie Not Found"));
            // set order detail movie
            orderDetail.setMovie(movie);
            // set order detail order
            orderDetail.setOrder(order);
            // set order detail purchase price
            orderDetail.setPurchasePrice(item.getPurchasePrice());
            
            orderDetails.add(orderDetail);
            
            total += orderDetail.getPurchasePrice();
        }
        // set order total price
        order.setTotalPrice(total);
        
        // set order details
        order.setOrderDetails(orderDetails);
        return orderRepository.save(order);
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
    
    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotfoundException("Order not found"));
    }
    
}
