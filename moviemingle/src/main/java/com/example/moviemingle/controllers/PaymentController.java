package com.example.moviemingle.controllers;

import com.example.moviemingle.dtos.order.OrderUpdateStatusDTO;
import com.example.moviemingle.entities.*;
import com.example.moviemingle.services.orders.OrderService;
import com.example.moviemingle.services.payments.PaymentGatewayService;
import com.example.moviemingle.services.payments.PaymentService;
import com.example.moviemingle.services.userMovies.UserMovieService;
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
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private UserMovieService userMovieService;
    
    @GetMapping("stripe/success")
    public ResponseEntity<Void> paymentResult(@RequestParam("session_id") String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);
        String status = session.getStatus();
        String redirectUrl = "";
        Long orderId = Long.parseLong(session.getClientReferenceId());
        // handle order and payment status update here
        Order order = orderService.getById(orderId);
        switch(status){
            case "complete" -> {
                redirectUrl = frontendUrl+"/payment?status=complete";
                orderService.updateOrderStatus(orderId, new OrderUpdateStatusDTO(OrderStatus.COMPLETED.name()));
                paymentService.updatePaymentStatus(orderId, PaymentStatus.COMPLETE.ordinal());
                userMovieService.createUserMovie(order);
            }
            case "expired" -> {
                redirectUrl = frontendUrl+"/payment?status=expired";
                orderService.updateOrderStatus(orderId, new OrderUpdateStatusDTO(OrderStatus.CANCELLED.name()));
                paymentService.updatePaymentStatus(orderId, PaymentStatus.EXPIRED.ordinal());
            }
            case "open" -> {
                redirectUrl = frontendUrl+"/payment?status=open";
            }
            default -> {
                redirectUrl = frontendUrl+"/payment?status=error";
                paymentService.updatePaymentStatus(orderId, PaymentStatus.ERROR.ordinal());
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
