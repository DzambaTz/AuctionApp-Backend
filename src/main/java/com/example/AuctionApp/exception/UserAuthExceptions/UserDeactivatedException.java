package com.example.AuctionApp.exception.UserAuthExceptions;

public class UserDeactivatedException extends UserAuthException {
    private static final long serialVersionUID = 3L;

    public UserDeactivatedException(String exceptionMessage) {
        super.setMessage(exceptionMessage);
        super.setStatusCode(423);
    }
}
