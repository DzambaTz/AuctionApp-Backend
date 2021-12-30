package com.example.AuctionApp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserItemResponse {
    BigInteger itemId;
    String imageUrl;
    String name;
    String timeLeft;
    Float startPrice;
    BigInteger numberOfBids;
    Float highestBid;

    public UserItemResponse(Tuple userItem){
        itemId = (BigInteger) userItem.get(0);
        imageUrl = (String) userItem.get(1);
        name = (String) userItem.get(2);
        timeLeft = (String) userItem.get(3);
        startPrice = (Float) userItem.get(4);
        numberOfBids = (BigInteger) userItem.get(5);
        highestBid = (Float) userItem.get(6);
    }
}
