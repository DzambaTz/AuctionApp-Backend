package com.example.AuctionApp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Tuple;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBidsItemResponse {
    BigInteger itemId;
    Float bidAmount;
    String imageUrl;
    String name;
    String timeLeft;
    BigInteger count;
    Float maxBidAmount;

    public UserBidsItemResponse(Tuple userItem){
        itemId = (BigInteger) userItem.get(0);
        bidAmount = (Float) userItem.get(1);
        imageUrl = (String) userItem.get(2);
        name = (String) userItem.get(3);
        timeLeft = (String) userItem.get(4);
        count = (BigInteger) userItem.get(5);
        maxBidAmount = (Float) userItem.get(6);
    }
}
