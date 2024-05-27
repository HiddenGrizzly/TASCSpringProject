package com.example.moviemingle.controllers;

import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.entities.User;
import com.example.moviemingle.services.payments.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.LineItem;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.net.StripeResponse;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("payments")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("checkout")
    public ResponseEntity<?> checkout() {
        User user = new User();
        user.setEmail("email@mail.com");
        
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movie1.setImdbId("asd123");
        movie1.setPrice(12.00);
        movie1.setMovieTitle("Avenger");
        movie2.setImdbId("123rty");
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
        order.setId(144L);
        order.setOrderDetails(orderDetails);
        order.setUser(user);
        
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (var item : order.getOrderDetails()) {
            Price price = Price.create(PriceCreateParams.builder()
                    .setCurrency("usd")
                    .setUnitAmountDecimal(BigDecimal.valueOf(item.getPurchasePrice() * 100))
                    .setProductData(PriceCreateParams.ProductData.builder().setName(item.getMovie().getMovieTitle()).build())
                    .build());
            lineItems.add(SessionCreateParams.LineItem.builder()
                    .setPrice(price.getId())
                    .setQuantity(1L)
                    .build());
        }
        
        SessionCreateParams params = SessionCreateParams.builder()
                .addAllLineItem(lineItems)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCustomerEmail(order.getUser().getEmail())
                .setCurrency("usd")
                .setSuccessUrl(serverUrl + "/success")
                .setCancelUrl(serverUrl + "/cancel")
                .build();
        
        Session session = Session.create(params);
        
        //URI uri = new URI(session.getUrl());
        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setLocation(uri);
        //return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        return ResponseEntity.ok(session.getUrl());
    }
}
