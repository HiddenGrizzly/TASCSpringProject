package com.example.moviemingle.controllers;

import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.entities.User;
import com.example.moviemingle.services.payments.PaymentService;
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
    private PaymentService paymentService;
    
    @Value("${frontend.base-url}")
    private String frontendUrl;
    
    // only for testing purpose
    @PostMapping
    public String testPayment() throws StripeException {
        User user = new User();
        user.setEmail("email@mail.com");
        
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movie1.setPrice(12.00);
        movie1.setMovieTitle("Avenger");
        movie2.setPrice(16.00);
        movie2.setMovieTitle("Titanic");
        
        Set<OrderDetail> orderDetails = new HashSet<>();
        OrderDetail od1 = new OrderDetail();
        od1.setMovie(movie1);
        od1.setPurchasePrice(12.00);
        
        OrderDetail od2 = new OrderDetail();
        od2.setMovie(movie2);
        od2.setPurchasePrice(16.00);
        
        orderDetails.add(od1);
        orderDetails.add(od2);
        
        Order order = new Order();
        order.setId(123L);
        order.setOrderDetails(orderDetails);
        order.setUser(user);
        
        return paymentService.createPaymentSession(order);
        
    }
    
    @GetMapping("success")
    public ResponseEntity<Void> paymentResult(@RequestParam("session_id")String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);
        String status = session.getStatus();
        String redirectUrl = "";
        String orderId = session.getClientReferenceId();
        
        // handle order status update here
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
}
