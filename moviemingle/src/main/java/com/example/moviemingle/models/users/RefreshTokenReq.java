package com.example.moviemingle.models.users;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenReq {
    
    @NotNull
    private String refreshToken;
    
}
