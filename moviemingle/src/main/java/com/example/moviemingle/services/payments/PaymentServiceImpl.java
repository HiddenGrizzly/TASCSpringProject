package com.example.moviemingle.services.payments;

import com.example.moviemingle.entities.Order;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    
    @Value("${server.base-url}")
    private String serverUrl;
    
    @Value("${payment.stripe.api-key}")
    private String apiKey;
    
    @PostConstruct
    protected void setupStripe(){
        Stripe.apiKey = apiKey;
    }
    
    @Override
    public String createPaymentSession(Order order) throws StripeException {
        
        SessionCreateParams params = SessionCreateParams.builder()
                .addAllLineItem(lineItems)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCustomerEmail(order.getUser().getEmail())
                .setCurrency("usd")
                .setSuccessUrl(serverUrl + "payments/result")
                .setClientReferenceId(order.getId())
                .build();
        
        return Session.create(params).getUrl();
    }
    
    //public PriceCreateParams.ProductData
    
}
