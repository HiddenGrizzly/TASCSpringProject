package com.example.moviemingle.controllers;

import com.example.moviemingle.dtos.movies.MovieDTO;
import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.User;
import com.example.moviemingle.models.orders.OrderCreateReq;
import com.example.moviemingle.models.pages.PageRes;
import com.example.moviemingle.models.users.ChangePasswordReq;
import com.example.moviemingle.models.users.UserMapper;
import com.example.moviemingle.models.users.UserRes;
import com.example.moviemingle.models.users.UserUpdateReq;
import com.example.moviemingle.services.movies.MovieService;
import com.example.moviemingle.services.orders.OrderService;
import com.example.moviemingle.services.payments.PaymentGatewayService;
import com.example.moviemingle.services.users.UserService;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("self")
public class SelfController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PaymentGatewayService paymentGatewayService;
    
    @Autowired
    private MovieService movieService;
    
    @GetMapping
    public UserRes getProfile(@AuthenticationPrincipal User user){
        return Mappers.getMapper(UserMapper.class).toUserRes(user);
    }
    
    @PutMapping
    public UserRes updateProfile(@RequestBody @Valid UserUpdateReq req, @AuthenticationPrincipal User user){
        
        return Mappers.getMapper(UserMapper.class).toUserRes(userService.updateUser(user, req));
    }
    
    @PatchMapping("passwords")
    public void changePassword(@RequestBody @Valid ChangePasswordReq req, @AuthenticationPrincipal User user){
        userService.changePassword(user, req);
    }
    
    @PatchMapping(value = "avatars", consumes = {"multipart/form-data"})
    public void changeAvatar(@RequestBody MultipartFile avatar, @AuthenticationPrincipal User user){
        userService.changeAvatar(user, avatar);
    }
    
    @PostMapping("orders")
    public ResponseEntity<?> createUserOrder(@RequestBody @Valid OrderCreateReq req, @AuthenticationPrincipal User user) throws StripeException {
        Order newOrder = orderService.createUserOrder(user, req);
        String paymentUrl = paymentGatewayService.createPaymentSession(newOrder);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("paymentUrl", paymentUrl));
        
    }
    
    @GetMapping("movies")
    public PageRes<MovieDTO> showMovieList(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @AuthenticationPrincipal User user
    ){
        Page<MovieDTO> moviePage = movieService.getUserMovies(user, pageable);
        return new PageRes<>(moviePage);
    }
    
}
