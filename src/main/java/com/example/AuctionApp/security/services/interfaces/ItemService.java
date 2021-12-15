/**
 * ItemService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */


package com.example.AuctionApp.security.services.interfaces;


import com.example.AuctionApp.payload.request.SearchItemRequest;
import org.springframework.http.ResponseEntity;

public interface ItemService {
    /**
     * getItemData method returns item data for item preview page
     *
     * @param itemId Id of the item that the user is trying to view
     * @return ItemDataResponse object that contains all the item data
     */
    ResponseEntity<?> getItemData(Long itemId);

    /**
     * getNewArrivals method returns item sorted by auction time remaining in descending
     * order
     *
     * @return List<Item> list of items sorted by auction time remaining in descending order
     */
    ResponseEntity<?> getNewArrivals();

    /**
     * getLastChanceItems method returns item sorted by auction time remaining in ascending
     * order
     *
     * @return List<Item> list of items sorted by auction time remaining in ascending order
     */
    ResponseEntity<?> getLastChanceItems();

    /**
     * getFilteredItems method returns a list of items that fit the search and filter criterion
     *
     * @param searchItemRequest object that contains the search keyword and filters that should be applied
     * @return List<Item> list of items that pass the filters and fit the search criterion
     */
    ResponseEntity<?> getFilteredItems(SearchItemRequest searchItemRequest);

    /**
     * getItemPriceLimits method returns the price of the cheapest and price of the most expensive item
     * in database used for determining the price filter slider range
     *
     * @return List<Float> list with two entries: min and max price of items in database
     */
    ResponseEntity<?> getItemPriceLimits();
}
