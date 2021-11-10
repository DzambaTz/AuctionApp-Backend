package com.example.AuctionApp.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BidRequest {
    private Float amount;
}
