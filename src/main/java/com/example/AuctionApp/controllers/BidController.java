/**
 * BidController is a class that provides API endpoints for the bidding process.
 * Currently, there are endpoints for placing a new bid, and for fetching the
 * number of bids on a specific item.
 *
 * @author Tarik Dzambic
 */

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

    @PostMapping(path= "/place/{itemId}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> placeBid(@Valid @RequestBody BidRequest request,
                                      @PathVariable("itemId") Long itemId,
                                      @RequestHeader("Authorization") String jwt) {
        return biddingService.placeBid(request, itemId, jwt);
    }

    @PostMapping(path = "/count/{itemId}")
    public Integer countBids(@PathVariable("itemId") Long itemId){
        return biddingService.countBids(itemId);
    }
}
