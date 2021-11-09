package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.models.Bid;
import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.BidRequest;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.repository.BidRepository;
import com.example.AuctionApp.repository.ItemRepository;
import com.example.AuctionApp.repository.UserRepository;
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

    public ResponseEntity<?> placeBid(BidRequest request) {
        final Item item = itemRepository.getById(request.getItemId());
        final User user = userRepository.getById(request.getUserId());
        final Instant date = new Date().toInstant();

        if(bidHasInvalidAmount(item, request.getAmount())){
            return ResponseEntity.badRequest().body(new MessageResponse("Your bid is smaller than or equal to the current max bid."));
        }

        if(auctionEnded(item,date)){
            return ResponseEntity.badRequest().body(new MessageResponse("The auction for the current item has finished."));
        }

        bidRepository.save(new Bid(item,user, request.getAmount(), date));
        return ResponseEntity.ok(new MessageResponse("Bid placed!"));
    }

    private Boolean bidHasInvalidAmount(Item item, Float bidAmount){
        return bidAmount < item.getStart_price() || bidAmount <= bidRepository.findLargestBid(item.getId());
    }

    private Boolean auctionEnded(Item item, Instant date){
        return  date.compareTo(item.getEnd_time()) > 0;
    }
}
