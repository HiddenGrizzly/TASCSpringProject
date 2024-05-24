package com.example.moviemingle.controllers;

import com.example.moviemingle.entities.User;
import com.example.moviemingle.models.pages.PageRes;
import com.example.moviemingle.models.users.UserMapper;
import com.example.moviemingle.models.users.UserRes;
import com.example.moviemingle.services.users.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public PageRes<UserRes> getAllUsers(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<User> users= userService.getAll(pageable);
        return new PageRes<>(users.map(Mappers.getMapper(UserMapper.class)::toUserRes));
    }
    
    @GetMapping("{id}")
    public UserRes getById(@PathVariable Long id){
        return Mappers.getMapper(UserMapper.class).toUserRes(userService.getById(id));
    }
    
}
