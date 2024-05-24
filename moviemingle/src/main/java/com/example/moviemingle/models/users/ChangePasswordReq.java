package com.example.moviemingle.models.users;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordReq {
    
    @NotBlank
    private String oldPassword;
    
    @NotBlank
    private String newPassword;
    
}
