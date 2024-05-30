package com.example.moviemingle.dtos.order;

import com.example.moviemingle.entities.Movie;
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
