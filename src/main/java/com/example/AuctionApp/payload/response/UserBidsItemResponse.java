package com.example.AuctionApp.payload.response;

import java.math.BigInteger;

public interface UserBidsItemResponse {
    BigInteger getItemId();

    Float getBidAmount();

    String getImageUrl();

    String getName();

    String getTimeLeft();

    BigInteger getCount();

    Float getMaxBidAmount();
}
