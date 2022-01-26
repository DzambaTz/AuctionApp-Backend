package com.example.AuctionApp.exception.UserAuthExceptions;

public class UserDoesNotExistException extends UserAuthException{
    private static final long serialVersionUID = 5L;

    public UserDoesNotExistException(String exceptionMessage) {
        super.setMessage(exceptionMessage);
        super.setStatus(404);
    }
}
