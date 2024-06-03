package com.example.moviemingle.services.payments;

public interface PaymentService {
    
    void updatePaymentStatus(Long orderId, int paymentStatus);
    
}
