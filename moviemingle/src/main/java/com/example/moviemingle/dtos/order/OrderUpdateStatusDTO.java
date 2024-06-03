package com.example.moviemingle.dtos.order;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateStatusDTO {
    @NotBlank
    private String orderStatus;
}
