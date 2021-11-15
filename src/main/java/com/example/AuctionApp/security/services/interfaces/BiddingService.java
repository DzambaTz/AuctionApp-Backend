/**
 * BiddingService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.payload.request.BidRequest;
import org.springframework.http.ResponseEntity;

public interface BiddingService {
    ResponseEntity<?> placeBid(BidRequest request, Long id, String jwt);

    Integer countBids(Long itemId);
}
