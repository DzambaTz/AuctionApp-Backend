package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.payload.request.BidRequest;
import org.springframework.http.ResponseEntity;

public interface BiddingService {
    ResponseEntity<?> placeBid(BidRequest request);
}
