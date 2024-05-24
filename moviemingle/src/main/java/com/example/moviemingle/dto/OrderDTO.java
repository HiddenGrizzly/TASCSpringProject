package com.example.moviemingle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String orderStatus;
    private Long userId;
    private Long paymentId;
//    private Long orderDetailId;
}
