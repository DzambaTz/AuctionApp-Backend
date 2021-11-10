package com.example.AuctionApp.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
public class ItemDataResponse {
    private Float startingPrice;
    private Float highestBid;
    private Integer numberOfBids;
    private Duration endTime;
    private String itemDescription;
    private String itemName;
}
