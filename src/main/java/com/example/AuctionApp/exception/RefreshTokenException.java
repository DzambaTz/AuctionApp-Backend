/**
 * RefreshTokenException is a custom exception thrown during a JWT refresh request.
 * If the sent refreshToken has expired, then a RefreshTokenException will be thrown.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RefreshTokenException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}