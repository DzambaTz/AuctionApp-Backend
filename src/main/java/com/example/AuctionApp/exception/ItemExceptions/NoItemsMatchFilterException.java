package com.example.AuctionApp.exception.ItemExceptions;

public class NoItemsMatchFilterException extends ItemException {
    private static final long serialVersionUID = 6L;

    public NoItemsMatchFilterException(String exceptionMessage) {
        super.setMessage(exceptionMessage);
        super.setStatusCode(404);
    }
}
