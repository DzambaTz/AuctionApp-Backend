/**
 * RoleNotFoundException is a custom exception thrown during user registration.
 * A roles array is sent as a part of the signup request. If while looping through the array,
 * a role that doesn't exist is detected, the RoleNotFoundException is thrown.
 *
 * @author Tarik Dzambic
 */

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
