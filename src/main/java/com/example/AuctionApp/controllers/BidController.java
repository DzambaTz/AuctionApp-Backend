package com.example.AuctionApp.controllers;

import com.example.AuctionApp.payload.request.BidRequest;
import com.example.AuctionApp.security.services.interfaces.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bid")
public class BidController {

    @Autowired
    BiddingService biddingService;

    @PostMapping("/")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> placeBid(@Valid @RequestBody BidRequest request) {
        return biddingService.placeBid(request);
    }
}
