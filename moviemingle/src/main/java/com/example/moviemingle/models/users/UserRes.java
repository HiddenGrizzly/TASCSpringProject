package com.example.moviemingle.models.users;

import com.example.moviemingle.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserRes {
    
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String username;
    
    private String email;
    
    private String avatar;
    
    private Set<String> roles = new HashSet<>();
    
    public UserRes(Long id, String firstName, String lastName, String username, String email, String avatar, Set<RoleType> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.roles = roles.stream().map(Enum::name).collect(Collectors.toSet());
    }
    
}
