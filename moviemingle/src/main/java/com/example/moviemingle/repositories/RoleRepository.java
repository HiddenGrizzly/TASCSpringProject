package com.example.moviemingle.repositories;

import com.example.moviemingle.entities.Role;
import com.example.moviemingle.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByRoleName(RoleType roleName);
}
