/**
 * TokenControllerAdvice class is an exception interceptor, that listens for RefreshTokenExceptions,
 * and constructs an ErrorMessage response object that is then returned to the refresh token request
 * sender.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.advice;

import com.example.AuctionApp.exception.RefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = RefreshTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleTokenRefreshException(RefreshTokenException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}