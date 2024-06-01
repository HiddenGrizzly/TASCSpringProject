package com.example.moviemingle.models.orders;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    
    @NotNull
    private Long movieId;

    @Min(0)
    @NotNull
    private Double purchasePrice;

}
