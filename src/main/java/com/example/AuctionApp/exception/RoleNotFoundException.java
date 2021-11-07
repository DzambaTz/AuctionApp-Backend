package com.example.AuctionApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RoleNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 2L;

    public RoleNotFoundException(String message){
        super(message);
    }
}
