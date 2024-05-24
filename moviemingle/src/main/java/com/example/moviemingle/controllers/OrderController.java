package com.example.moviemingle.controllers;

import com.example.moviemingle.dto.OrderDTO;
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
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrder();
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
    @PostMapping("/add")
    public void addOrder(@RequestBody OrderDTO orderDto) {
        orderService.addOrder(orderDto);
    }
    @PutMapping("/update/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDto) {
        orderService.updateOrder(id, orderDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
