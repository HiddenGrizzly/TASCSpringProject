package com.example.moviemingle.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleType roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    
}
