/**
 * BiddingServiceImpl is a class that implements methods for the BiddingService interface.
 * It contains all of the logic behind the bidding process, and it is the one that communicates
 * with the BidRepository.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.models.Bid;
import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.BidRequest;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.repository.BidRepository;
import com.example.AuctionApp.repository.ItemRepository;
import com.example.AuctionApp.repository.UserRepository;
import com.example.AuctionApp.security.jwt.JwtUtils;
import com.example.AuctionApp.security.services.interfaces.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class BiddingServiceImpl implements BiddingService {

    @Autowired
    BidRepository bidRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;


    public ResponseEntity<?> placeBid(BidRequest request, Long itemId, String jwt) {
        final Item item = itemRepository.getById(itemId);
        final User user =  jwtUtils.getUserDetailsFromJwt(jwt.substring(7));
        final Instant date = new Date().toInstant();

        if(bidHasInvalidAmount(item, request.getAmount())){
            return ResponseEntity.badRequest().body(new MessageResponse("There are higher bids than yours. You could give a second try!"));
        }

        if(auctionEnded(item,date)){
            return ResponseEntity.badRequest().body(new MessageResponse("The auction for the current item has finished."));
        }

        bidRepository.save(new Bid(item,user, request.getAmount(), date));
        return ResponseEntity.ok(new MessageResponse("Congrats! You are the highest bidder"));
    }

    public Integer countBids(Long itemId){
        return bidRepository.countBids(itemId);
    }

    private Boolean bidHasInvalidAmount(Item item, Float bidAmount){
        return bidAmount < item.getStartPrice() || bidAmount <= (bidRepository.findLargestBid(item.getId()));
    }

    private Boolean auctionEnded(Item item, Instant date){
        return  date.compareTo(item.getEndTime()) > 0;
    }
}