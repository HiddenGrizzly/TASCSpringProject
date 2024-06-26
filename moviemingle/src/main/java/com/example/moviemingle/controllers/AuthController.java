package com.example.moviemingle.controllers;

import com.example.moviemingle.entities.User;
import com.example.moviemingle.models.users.*;
import com.example.moviemingle.services.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("register")
    public UserRes register(@RequestBody @Valid RegisterReq dto){
        User newUser = userService.register(dto);
        return Mappers.getMapper(UserMapper.class).toUserRes(newUser);
    }
    
    @PostMapping("login")
    public TokenRes login(@RequestBody @Valid LoginReq dto){
        return userService.login(dto);
    }
    
    
    @PostMapping("tokens")
    public TokenRes refreshToken(@RequestBody RefreshTokenReq req){
        return userService.refreshToken(req);
    }
    
    @GetMapping("test")
    public String test(){
        return "test";
    }
    
}
