package com.example.AuctionApp.exception.UserAuthExceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserAuthException extends Exception {
    Integer status;
    String message;
}
