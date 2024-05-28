package com.example.moviemingle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Double purchasePrice;

    public void setPurchasePrice() {
        if (movie != null) {
            this.purchasePrice = movie.getPrice();
        }
    }

    public Double getPurchasePrice() {
        if (movie != null) {
            return movie.getPrice();
        }
        return 0.0;
    }
    
}

