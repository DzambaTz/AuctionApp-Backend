package com.example.AuctionApp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BidRequest {
    private Long userId;
    private Float amount;
}
