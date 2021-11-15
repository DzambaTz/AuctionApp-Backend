/**
 * ItemService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */


package com.example.AuctionApp.security.services.interfaces;


import org.springframework.http.ResponseEntity;

public interface ItemService {
    ResponseEntity<?> getItemData(Long itemId);
    ResponseEntity<?> getNewArrivals();
    ResponseEntity<?> getLastChanceItems();
}
