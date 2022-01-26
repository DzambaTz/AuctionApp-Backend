package com.example.AuctionApp.exception.PaymentExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PaymentException extends Exception {
    Integer statusCode;
    String message;
}
