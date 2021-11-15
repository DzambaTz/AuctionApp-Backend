/**
 * BidRequest is a class used for incoming bid placement request mapping.
 *
 * @author Tarik Dzambic
 */

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
