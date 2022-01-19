package com.example.AuctionApp.payload.response;

import java.math.BigInteger;

public interface UserItemResponse {
    BigInteger getItemId();

    String getImageUrl();

    String getName();

    String getTimeLeft();

    Float getStartPrice();

    BigInteger getNumberOfBids();

    Float getHighestBid();
}
