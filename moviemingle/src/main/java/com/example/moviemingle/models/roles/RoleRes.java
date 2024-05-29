package com.example.moviemingle.models.roles;

import com.example.moviemingle.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRes {
    
    private Long id;
    
    private RoleType roleName;
    
}
