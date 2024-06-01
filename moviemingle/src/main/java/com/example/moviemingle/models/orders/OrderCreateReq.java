package com.example.moviemingle.models.orders;

import com.example.moviemingle.entities.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateReq {

    @NotNull
    private PaymentMethod paymentMethod;

    @NotEmpty
    private Set<OrderItem> items;
    
}
