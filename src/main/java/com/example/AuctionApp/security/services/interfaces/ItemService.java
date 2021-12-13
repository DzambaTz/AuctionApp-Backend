/**
 * ItemService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */


package com.example.AuctionApp.security.services.interfaces;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemService {
    ResponseEntity<?> getItemData(Long itemId);
    ResponseEntity<?> getNewArrivals();
    ResponseEntity<?> getLastChanceItems();
    ResponseEntity<?> getFilteredItems(List<String> category, List<String> subcategory, Float minPrice, Float maxprice, String search);
    ResponseEntity<?> getItemPriceLimits();
}
