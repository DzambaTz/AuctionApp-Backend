/**
 * ItemController is a class that provides API endpoints for item data fetching.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.controllers;

import com.example.AuctionApp.security.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/filter")
    public ResponseEntity<?> getFilteredItems(@RequestParam(name = "category") List<String> category,
                                              @RequestParam(name = "subcategory") List<String> subcategory,
                                              @RequestParam(name = "minprice") Float minPrice,
                                              @RequestParam(name = "maxprice") Float maxprice,
                                              @RequestParam(name = "search") String search
    ) {
        return itemService.getFilteredItems(category,subcategory,minPrice,maxprice,search);
    }

    @GetMapping(path = "/priceLimits")
    public ResponseEntity<?> getItemPriceLimits(){
        return itemService.getItemPriceLimits();
    }
}
