package com.example.moviemingle.controllers;

import com.example.moviemingle.entities.User;
import com.example.moviemingle.models.users.ChangePasswordReq;
import com.example.moviemingle.models.users.UserMapper;
import com.example.moviemingle.models.users.UserRes;
import com.example.moviemingle.models.users.UserUpdateReq;
import com.example.moviemingle.services.users.UserService;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("self")
public class SelfController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public UserRes getProfile(@AuthenticationPrincipal User user){
        return Mappers.getMapper(UserMapper.class).toUserRes(user);
    }
    
    @PutMapping
    public UserRes updateProfile(@RequestBody @Valid UserUpdateReq req, @AuthenticationPrincipal User user){
        
        return Mappers.getMapper(UserMapper.class).toUserRes(userService.updateUser(user, req));
    }
    
    @PatchMapping("password")
    public void changePassword(@RequestBody @Valid ChangePasswordReq req, @AuthenticationPrincipal User user){
        userService.changePassword(user, req);
    }
    
}
