/**
 * Default implementation for {@link ItemService}
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.payload.request.SearchItemRequest;
import com.example.AuctionApp.payload.response.ItemDataResponse;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.repository.BidRepository;
import com.example.AuctionApp.repository.ItemRepository;
import com.example.AuctionApp.security.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BidRepository bidRepository;

    public ResponseEntity<?> getItemData(Long itemId) {
        final Optional<Item> item = Optional.of(itemRepository.getById(itemId));
        final Float highestBid = bidRepository.findLargestBid(itemId);
        final Integer numberOfBids = bidRepository.countBids(itemId);
        final Duration endOfAuction = timeLeftTillEndOfAuction(item.get());

        return ResponseEntity.ok(new ItemDataResponse(
                item.get().getStartPrice(),
                highestBid,
                numberOfBids,
                endOfAuction,
                item.get().getDescription(),
                item.get().getName(),
                item.get().getImages()));
    }

    @Override
    public ResponseEntity<?> getNewArrivals() {
        return ResponseEntity.ok(itemRepository.findAllByOrderByStartTimeDesc());
    }

    @Override
    public ResponseEntity<?> getLastChanceItems() {
        return ResponseEntity.ok(itemRepository.findAllByOrderByStartTimeAsc());
    }

    private Duration timeLeftTillEndOfAuction(Item item){
        return item.getEndTime().compareTo(Instant.now()) <= 0 ? Duration.ZERO : Duration.between(item.getEndTime(), Instant.now());
    }

    @Override
    public ResponseEntity<?> getFilteredItems(SearchItemRequest searchItemRequest){
        if(searchItemRequest.getMinPrice() == 0 && searchItemRequest.getMaxPrice() == 0){
            searchItemRequest.setMinPrice(itemRepository.getMinPrice());
            searchItemRequest.setMaxPrice(itemRepository.getMaxPrice());
        }

        if (CollectionUtils.isEmpty(searchItemRequest.getCategory()) && CollectionUtils.isEmpty(searchItemRequest.getSubcategory())) {
            searchItemRequest.setCategory(itemRepository.getListOfCategories());
            searchItemRequest.setSubcategory(itemRepository.getListOfSubcategories());
        }

        final List<Item> filteredItems = itemRepository.getFilteredItems(searchItemRequest);

        if(CollectionUtils.isEmpty(filteredItems)){
            return ResponseEntity.badRequest().body(new MessageResponse("No items match your filters!"));
        }

        return ResponseEntity.ok(filteredItems);
    }

    @Override
    public ResponseEntity<?> getItemPriceLimits() {
        List<Float> itemPrices = new ArrayList<>();
        itemPrices.add(itemRepository.getMinPrice());
        itemPrices.add(itemRepository.getMaxPrice());

        return ResponseEntity.ok(itemPrices);
    }
}
