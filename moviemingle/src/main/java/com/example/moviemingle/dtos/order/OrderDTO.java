package com.example.moviemingle.dtos.order;

import com.example.moviemingle.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String orderStatus;
    private Long userId;
    private String userName;
//    private Long paymentId;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private Set<OrderDetailDTO> orderDetails;
}
