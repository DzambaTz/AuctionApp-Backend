/**
 * BiddingService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.interfaces;

import com.example.AuctionApp.payload.request.BidRequest;
import com.example.AuctionApp.payload.response.UserBidsItemResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BiddingService {
    /**
     * placeBid method parses an incoming BidRequest objects, and if the bid amount is valid, and the
     * auction is still active then the bid will be placed
     *
     * @param request Object that contains the amount a user wants to bid
     * @param itemId  ID of the item that the user is trying to bid for
     * @param userId  ID of the user trying to bid
     * @return Message response if the bid was placed successfully or not
     */
    ResponseEntity<?> placeBid(BidRequest request, Long itemId, Long userId);

    /**
     * countBids method returns the number of bids for a single item
     *
     * @param itemId Id of the item that the user is trying to get number of bids for
     * @return Integer that represents the number of bids
     */
    Integer countBids(Long itemId);

    /**
     * getUserBids method returns all of the bids a user placed and information about the
     * items that the user has place the bids on
     *
     * @param userId ID of the user trying to access his bids
     * @return List<UserBidsItemResponse> that contains the bidding and item data
     */
    List<UserBidsItemResponse> getUserBids(Long userId);
}
