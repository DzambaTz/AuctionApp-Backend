/**
 * BiddingService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.payload.request.BidRequest;
import org.springframework.http.ResponseEntity;

public interface BiddingService {
    /**
     * placeBid method parses an incoming BidRequest objects, and if the bid amount is valid, and the
     * auction is still active then the bid will be placed
     *
     * @param request Object that contains the amount a user wants to bid
     * @param id Id of the item that the user is trying to bid for
     * @param jwt JWT of the user trying to bid
     * @return Message response if the bid was placed successfully or not
     */
    ResponseEntity<?> placeBid(BidRequest request, Long id, String jwt);

    /**
     * countBids method returns the number of bids for a single item
     *
     * @param itemId Id of the item that the user is trying to get number of bids for
     * @return Integer that represents the number of bids
     */
    Integer countBids(Long itemId);
}
