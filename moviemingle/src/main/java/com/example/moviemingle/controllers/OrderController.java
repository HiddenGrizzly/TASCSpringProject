package com.example.moviemingle.controllers;

import com.example.moviemingle.dtos.order.OrderDTO;
import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.dtos.order.OrderUpdateStatusDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderStatus;
import com.example.moviemingle.mappers.OrderMapper;
import com.example.moviemingle.models.pages.PageRes;
import com.example.moviemingle.services.orders.OrderService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class    OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public PageRes<OrderDTO> getAllOrders(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {
        Page<OrderDTO> ordersPage = orderService.getAllOrder(pageable);
        return new PageRes<>(ordersPage);
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

    @GetMapping("/{orderId}/detail")
    public List<OrderDetailDTO> getOrderDetailByOrderId(@PathVariable Long orderId) {
        return orderService.getOrderDetailByOrderId(orderId);
    }

    @GetMapping("/user/{username}")
    public List<OrderDTO> getOrderByUsername(@PathVariable String username) {
        return orderService.getOrderByUsername(username);
    }

    @GetMapping("/users/{userId}")
    public List<OrderDTO> getOrderByUserId(@PathVariable Long userId) {
        return orderService.getOrderByUserId(userId);
    }

    @GetMapping("/month/{month}")
    public List<OrderDTO> getAllOrdersInMonth(@PathVariable int month) {
        return orderService.getAllOrdersInMonth(month);
    }

    @PostMapping
    public void createOrder(@RequestBody OrderDTO orderDto) {
        orderService.createOrder(orderDto);
    }

    @PatchMapping("/{id}")
    public void updateOrderStatus(@PathVariable Long id, @RequestBody @Valid OrderUpdateStatusDTO dto) {
        orderService.updateOrderStatus(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
