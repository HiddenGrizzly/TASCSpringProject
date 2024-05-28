package com.example.moviemingle.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    private String orderStatus;

    private Double totalPrice;
    public void setTotalPrice() {
        if (orderDetails != null && !orderDetails.isEmpty()) {
            this.totalPrice = orderDetails.stream().filter(orderDetail -> orderDetail.getOrder().getId().equals(this.getId()))
                    .mapToDouble(OrderDetail::getPurchasePrice)
                    .sum();
        } else {
            this.totalPrice = 0.0;
        }
    }
}
