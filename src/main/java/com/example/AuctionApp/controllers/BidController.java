/**
 * BidController is a class that provides API endpoints for the bidding process.
 * Currently, there are endpoints for placing a new bid, and for fetching the
 * number of bids on a specific item.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.controllers;

import com.example.AuctionApp.payload.request.BidRequest;
import com.example.AuctionApp.payload.response.UserBidsItemResponse;
import com.example.AuctionApp.security.services.implementations.UserDetailsImpl;
import com.example.AuctionApp.security.services.interfaces.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bid")
public class BidController {

    @Autowired
    BiddingService biddingService;

    @PostMapping("/{itemId}/place")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> placeBid(@Valid @RequestBody BidRequest request,
                                      @PathVariable("itemId") Long itemId,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return biddingService.placeBid(request, itemId, userDetails.getId());
    }

    @PostMapping("/{itemId}/count")
    public Integer countBids(@PathVariable("itemId") Long itemId) {
        return biddingService.countBids(itemId);
    }

    @GetMapping("/user-bids")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<UserBidsItemResponse>> getUserBids(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(biddingService.getUserBids(userDetails.getId()));
    }
}
