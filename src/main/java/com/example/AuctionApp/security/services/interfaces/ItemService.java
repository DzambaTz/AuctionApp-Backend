/**
 * ItemService is an interface that exposes public methods for usage inside the controllers.
 *
 * @author Tarik Dzambic
 */


package com.example.AuctionApp.security.services.interfaces;


import com.example.AuctionApp.exception.ItemExceptions.NoItemsMatchFilterException;
import com.example.AuctionApp.exception.UserAuthExceptions.UserDoesNotExistException;
import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.payload.request.SearchItemRequest;
import com.example.AuctionApp.payload.response.ItemDataResponse;
import com.example.AuctionApp.payload.response.UserItemResponse;

import java.util.List;

public interface ItemService {
    /**
     * getItemData method returns item data for item preview page
     *
     * @param itemId Id of the item that the user is trying to view
     * @return ItemDataResponse object that contains all the item data
     */
    ItemDataResponse getItemData(Long itemId);

    /**
     * getNewArrivals method returns item sorted by auction time remaining in descending
     * order
     *
     * @return List<Item> list of items sorted by auction time remaining in descending order
     */
    List<Item> getNewArrivals();

    /**
     * getLastChanceItems method returns item sorted by auction time remaining in ascending
     * order
     *
     * @return List<Item> list of items sorted by auction time remaining in ascending order
     */
    List<Item> getLastChanceItems();

    /**
     * getFilteredItems method returns a list of items that fit the search and filter criterion
     *
     * @param searchItemRequest object that contains the search keyword and filters that should be applied
     * @return List<Item> list of items that pass the filters and fit the search criterion
     */
    List<Item> getFilteredItems(SearchItemRequest searchItemRequest) throws NoItemsMatchFilterException;

    /**
     * getItemPriceLimits method returns the price of the cheapest and price of the most expensive item
     * in database used for determining the price filter slider range
     *
     * @return List<Float> list with two entries: min and max price of items in database
     */
    List<Float> getItemPriceLimits();

    /**
     * getActiveUserItems method returns a list of active items that a specific user posted,
     * specified by the userId parameter.
     *
     * @param userId ID of the user that we are fetching the active items for
     * @return List<UserItemResponse> list of objects that contain data about the active items
     */
    List<UserItemResponse> getActiveUserItems(Long userId);

    /**
     * getSoldUserItems method returns a list of sold items (the auction period for them has finished).
     *
     * @param userId ID of the user trying to access his sold items
     * @return List<Float> list with two entries: min and max price of items in database
     */
    List<UserItemResponse> getSoldUserItems(Long userId);

    Item addNewItem(Long userId, Item item) throws UserDoesNotExistException;
}
