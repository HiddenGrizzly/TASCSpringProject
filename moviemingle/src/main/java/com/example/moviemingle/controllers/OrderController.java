package com.example.moviemingle.controllers;

import com.example.moviemingle.dto.OrderDTO;
import com.example.moviemingle.dto.OrderDetailDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.mappers.OrderMapper;
import com.example.moviemingle.services.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public List<OrderDTO> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return orderService.getAllOrder(page, size);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO orderDto = orderService.getOrderById(id);
        if (orderDto != null) {
            return ResponseEntity.ok(orderDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{username}")
    public List<OrderDTO> getOrderByUsername(@PathVariable String username) {
        return orderService.getOrderByUsername(username);
    }
    @PostMapping
    public void createOrder(@RequestBody OrderDTO orderDto) {
        orderService.createOrder(orderDto);
    }
//    @PostMapping("/detail/{orderId}")
//    public OrderDTO addOrderDetail(@PathVariable Long orderId, @RequestBody OrderDetailDTO orderDetailDTO) {
//        return orderService.addOrderDetail(orderId, orderDetailDTO);
//    }
    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDto) {
        orderService.updateOrder(id, orderDto);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
