package com.example.moviemingle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedRequestException extends ResponseStatusException {
    public UnauthorizedRequestException (){
        super(HttpStatus.UNAUTHORIZED, "Unauthorized Request");
    }
}
