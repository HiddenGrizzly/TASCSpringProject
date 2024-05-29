package com.example.moviemingle.controllers;

import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.entities.User;
import com.example.moviemingle.services.payments.PaymentGatewayService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("payments")
public class PaymentController {
    
    @Autowired
    private PaymentGatewayService paymentGatewayService;
    
    @Value("${frontend.base-url}")
    private String frontendUrl;
    
    @GetMapping("stripe/success")
    public ResponseEntity<Void> paymentResult(@RequestParam("session_id") String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);
        String status = session.getStatus();
        String redirectUrl = "";
        String orderId = session.getClientReferenceId();
        // handle order and payment status update here
        
        switch(status){
            case "complete" -> {
                redirectUrl = frontendUrl+"/payment?status=complete";
            }
            case "expired" -> {
                redirectUrl = frontendUrl+"/payment?status=expired";
            }
            case "open" -> {
                redirectUrl = frontendUrl+"/payment?status=open";
            }
            default -> {
                redirectUrl = frontendUrl+"/payment?status=error";
            }
        }
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .location(URI.create(redirectUrl))
                .build();
    }
    
    @GetMapping("stripe/cancel")
    public ResponseEntity<Void> cancelPayment(){
        // handle order and payment update here
        
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .location(URI.create(frontendUrl+"/payment?status=cancel"))
                .build();
    }
    
}
