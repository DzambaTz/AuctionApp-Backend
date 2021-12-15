/**
 * ItemController is a class that provides API endpoints for item data fetching.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.controllers;

import com.example.AuctionApp.payload.request.SearchItemRequest;
import com.example.AuctionApp.security.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping(path = "/{itemId}")
    public ResponseEntity<?> getItemData(@PathVariable("itemId") Long itemId) {
        return itemService.getItemData(itemId);
    }

    @GetMapping(path = "/newArrivals")
    public ResponseEntity<?> getNewArrivals() {
        return itemService.getNewArrivals();
    }

    @GetMapping(path = "/lastChance")
    public ResponseEntity<?> getLastChanceItems() {
        return itemService.getLastChanceItems();
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> getFilteredItems(SearchItemRequest searchItemRequest) {
        return itemService.getFilteredItems(searchItemRequest);
    }

    @GetMapping(path = "/priceLimits")
    public ResponseEntity<?> getItemPriceLimits(){
        return itemService.getItemPriceLimits();
    }
}
