package com.example.moviemingle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class DuplicateTitleException extends ResponseStatusException {
    public DuplicateTitleException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }
}
