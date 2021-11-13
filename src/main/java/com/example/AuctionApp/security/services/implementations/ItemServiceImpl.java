package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.payload.response.ItemDataResponse;
import com.example.AuctionApp.repository.BidRepository;
import com.example.AuctionApp.repository.ItemRepository;
import com.example.AuctionApp.security.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BidRepository bidRepository;

    public ResponseEntity<?> getItemData(Long itemId) {
        final Item item = itemRepository.getById(itemId);
        final Float highestBid = bidRepository.findLargestBid(itemId);
        final Integer numberOfBids = bidRepository.countBids(itemId);
        final Duration endOfAuction = timeLeftTillEndOfAuction(item);

        return ResponseEntity.ok(new ItemDataResponse(
                item.getStart_price(),
                highestBid,
                numberOfBids,
                endOfAuction,
                item.getDescription(),
                item.getName(),
                item.getImages()));
    }

    @Override
    public ResponseEntity<?> getNewArrivals() {
        return ResponseEntity.ok(itemRepository.getNewArrivals());
    }

    @Override
    public ResponseEntity<?> getLastChanceItems() {
        return ResponseEntity.ok(itemRepository.getLastChanceItems());
    }

    private Duration timeLeftTillEndOfAuction(Item item){
        return item.getEnd_time().compareTo(Instant.now()) <= 0 ? Duration.ZERO : Duration.between(item.getEnd_time(), Instant.now());
    }
}
