package com.example.AuctionApp.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemPaymentRequest {
    Long paymentAmount;

    String imageUrl;

    String name;

}
