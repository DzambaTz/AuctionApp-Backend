package com.example.AuctionApp.exception.UserAuthExceptions;

public class EmailAlreadyInUseException extends UserAuthException {
    private static final long serialVersionUID = 4L;

    public EmailAlreadyInUseException(String exceptionMessage) {
        super.setMessage(exceptionMessage);
        super.setStatus(409);
    }
}
