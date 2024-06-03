package com.example.moviemingle.controllers;

import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.services.orderdetail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order_detail")
public class    OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping
    public List<OrderDetailDTO> getAllOrderDetail(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return orderDetailService.getAllOrderDetail(page, size);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable Long id) {
        OrderDetailDTO orderDetailDTO = orderDetailService.getOrderDetailById(id);
        if (orderDetailDTO != null) {
            return ResponseEntity.ok(orderDetailDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/order/{orderId}")
    public List<OrderDetailDTO> getOrderDetailByOrderId(@PathVariable Long orderId) {
        return orderDetailService.getOrderDetailByOrderId(orderId);
    }
    @PostMapping
    public void addOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        orderDetailService.addOrderDetail(orderDetailDTO);
    }
    @PutMapping("/{id}")
    public void updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
        orderDetailService.updateOrderDetail(id, orderDetailDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
    }
}
