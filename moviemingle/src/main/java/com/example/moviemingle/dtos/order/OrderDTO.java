package com.example.moviemingle.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String orderStatus;
    private Long userId;
    private String userName;
    private Long paymentId;
    private Double totalPrice;
    private LocalDateTime createdAt;
//    private Set<OrderDetailDTO> orderDetails;
}
