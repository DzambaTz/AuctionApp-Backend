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
import java.time.LocalTime;

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

        return ResponseEntity.ok(new ItemDataResponse(
                item.getStart_price(),
                highestBid,
                numberOfBids,
                Duration.between(item.getEnd_time(), Instant.now()),
                item.getDescription(),
                item.getName()
                ));
    }
}
