package com.example.moviemingle.services.payments;

import com.example.moviemingle.entities.Order;
import com.stripe.exception.StripeException;

public interface PaymentGatewayService {

    String createPaymentSession(Order order) throws StripeException;

}
