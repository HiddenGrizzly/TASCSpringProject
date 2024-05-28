package com.example.moviemingle.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.moviemingle.entities.Role;
import com.example.moviemingle.entities.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    
    
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    
    @Value("${security.jwt.token.issuer}")
    private String issuer;
    
    private Algorithm algorithm;
    
    @PostConstruct
    protected void init(){
        algorithm = Algorithm.HMAC256(secretKey);
    }
    
    public String createAccessToken(User user){
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("username", user.getUsername())
                .withClaim("roles", user.getRoles().stream().map(Role::getRoleName).map(Enum::name).collect(Collectors.toList()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))
                .sign(algorithm);
    }
    
    public String createRefreshToken(User user){
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("username", user.getUsername())
                .withClaim("roles", user.getRoles().stream().map(Role::getRoleName).map(Enum::name).collect(Collectors.toList()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*24))
                .sign(algorithm);
    }
    
    public DecodedJWT decodeToken(String token){
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
    
    public String extractUsername(String token){
        return decodeToken(token).getClaim("username").asString();
    }
    
    public boolean isTokenValid(String token, User user){
        String username = extractUsername(token);
        return username.equals(user.getUsername());
    }
    
}
