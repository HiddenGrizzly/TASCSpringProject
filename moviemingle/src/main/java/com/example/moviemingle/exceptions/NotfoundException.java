package com.example.moviemingle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NotfoundException extends ResponseStatusException {
    public NotfoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
