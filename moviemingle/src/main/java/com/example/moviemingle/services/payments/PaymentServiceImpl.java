package com.example.moviemingle.services.payments;

import com.example.moviemingle.entities.Payment;
import com.example.moviemingle.exceptions.NotfoundException;
import com.example.moviemingle.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Override
    public void updatePaymentStatus(Long orderId, int paymentStatus) {
        Payment payment = paymentRepository.findByOrder_Id(orderId).orElseThrow(() -> new NotfoundException("Payment not found"));
        payment.setStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
