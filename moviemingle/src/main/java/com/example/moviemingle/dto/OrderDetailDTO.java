package com.example.moviemingle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Long orderId;
    private Long movieId;
    private String movieTitle;
    private Double moviePrice;
}
