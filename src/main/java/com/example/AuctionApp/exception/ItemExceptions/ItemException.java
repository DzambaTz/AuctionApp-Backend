package com.example.AuctionApp.exception.ItemExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ItemException extends Exception {
    Integer statusCode;
    String message;
}
