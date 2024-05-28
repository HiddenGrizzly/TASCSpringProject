package com.example.moviemingle.models.users;

import com.example.moviemingle.entities.Role;
import com.example.moviemingle.models.roles.RoleRes;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRes {
    
    private Long userId;
    
    private String username;
    
    private String avatar;
    
    private Set<String> roles = new HashSet<>();
    
    private String accessToken;
    
    private String refreshToken;
    
}
