package com.example.moviemingle.dtos.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderUpdateStatusDTO {
    @NotBlank
    private String orderStatus;
}
