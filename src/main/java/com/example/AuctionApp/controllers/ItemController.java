/**
 * ItemController is a class that provides API endpoints for item data fetching.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.controllers;

import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.payload.request.SearchItemRequest;
import com.example.AuctionApp.payload.response.ItemDataResponse;
import com.example.AuctionApp.payload.response.UserItemResponse;
import com.example.AuctionApp.security.services.implementations.UserDetailsImpl;
import com.example.AuctionApp.security.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDataResponse> getItemData(@PathVariable("itemId") Long itemId) {
        return ResponseEntity.ok(itemService.getItemData(itemId));
    }

    @GetMapping("/new-arrivals")
    public ResponseEntity<List<Item>> getNewArrivals() {
        return ResponseEntity.ok(itemService.getNewArrivals());
    }

    @GetMapping("/last-chance")
    public ResponseEntity<List<Item>> getLastChanceItems() {
        return ResponseEntity.ok(itemService.getLastChanceItems());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getFilteredItems(SearchItemRequest searchItemRequest) {
        return itemService.getFilteredItems(searchItemRequest);
    }

    @GetMapping("/price-limits")
    public ResponseEntity<List<Float>> getItemPriceLimits() {
        return ResponseEntity.ok(itemService.getItemPriceLimits());
    }

    @GetMapping("/active-items")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<UserItemResponse>> getUsersActiveItems(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(itemService.getActiveUserItems(userDetails.getId()));
    }

    @GetMapping("/sold-items")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<UserItemResponse>> getUsersSoldItems(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(itemService.getSoldUserItems(userDetails.getId()));
    }
}
