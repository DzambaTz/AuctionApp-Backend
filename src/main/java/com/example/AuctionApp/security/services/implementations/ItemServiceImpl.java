/**
 * Default implementation for {@link ItemService}
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.security.services.implementations;

import com.example.AuctionApp.exception.ItemExceptions.NoItemsMatchFilterException;
import com.example.AuctionApp.exception.UserAuthExceptions.UserDoesNotExistException;
import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.SearchItemRequest;
import com.example.AuctionApp.payload.response.ItemDataResponse;
import com.example.AuctionApp.payload.response.UserItemResponse;
import com.example.AuctionApp.repository.BidRepository;
import com.example.AuctionApp.repository.ItemRepository;
import com.example.AuctionApp.repository.UserRepository;
import com.example.AuctionApp.security.jwt.JwtUtils;
import com.example.AuctionApp.security.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    public ItemDataResponse getItemData(Long itemId) {
        final Optional<Item> item = Optional.of(itemRepository.getById(itemId));
        final Float highestBid = bidRepository.findLargestBid(itemId);
        final Integer numberOfBids = bidRepository.countBids(itemId);
        final Duration endOfAuction = timeLeftTillEndOfAuction(item.get());

        return new ItemDataResponse(
                item.get().getStartPrice(),
                highestBid,
                numberOfBids,
                endOfAuction,
                item.get().getDescription(),
                item.get().getName(),
                item.get().getImages());
    }

    @Override
    public List<Item> getNewArrivals() {
        return itemRepository.findAllByOrderByStartTimeDesc();
    }

    @Override
    public List<Item> getLastChanceItems() {
        return itemRepository.findAllByOrderByEndTimeAsc();
    }

    private Duration timeLeftTillEndOfAuction(Item item) {
        return item.getEndTime().compareTo(Instant.now()) <= 0 ? Duration.ZERO : Duration.between(item.getEndTime(), Instant.now());
    }

    @Override
    public List<Item> getFilteredItems(SearchItemRequest searchItemRequest) throws NoItemsMatchFilterException {
        if (searchItemRequest.getMinPrice() == 0 && searchItemRequest.getMaxPrice() == 0) {
            searchItemRequest.setMinPrice(itemRepository.getMinPrice());
            searchItemRequest.setMaxPrice(itemRepository.getMaxPrice());
        }

        if (CollectionUtils.isEmpty(searchItemRequest.getCategory()) && CollectionUtils.isEmpty(searchItemRequest.getSubcategory())) {
            searchItemRequest.setCategory(itemRepository.getListOfCategories());
            searchItemRequest.setSubcategory(itemRepository.getListOfSubcategories());
        }

        final List<Item> filteredItems = itemRepository.getFilteredItems(searchItemRequest, getSortingOrder(searchItemRequest));

        if (CollectionUtils.isEmpty(filteredItems)) {
            throw new NoItemsMatchFilterException("No items match your filters!");
        }

        return filteredItems;
    }

    @Override
    public List<Float> getItemPriceLimits() {
        List<Float> itemPrices = new ArrayList<>();
        itemPrices.add(itemRepository.getMinPrice());
        itemPrices.add(itemRepository.getMaxPrice());

        return itemPrices;
    }

    @Override
    public List<UserItemResponse> getActiveUserItems(Long userId) {
        return itemRepository.getActiveUserItems(userId);
    }

    @Override
    public List<UserItemResponse> getSoldUserItems(Long userId) {
        return itemRepository.getSoldUserItems(userId);
    }

    @Override
    public Item addNewItem(Long userId, Item item) throws UserDoesNotExistException {
        final Optional<User> user = userRepository.findUsersById(userId);
        if (user.isPresent()) {
            item.setUser(user.get());
            System.out.println(item);
            return itemRepository.addNewItem(item);
        }

        throw new UserDoesNotExistException("User not found!");
    }

    private PageRequest getSortingOrder(SearchItemRequest searchItemRequest) {
        return PageRequest.of(
                searchItemRequest.getPageNumber(),
                searchItemRequest.getPageSize(),
                Sort.Direction.fromString(searchItemRequest.getDirection().name()),
                getSortByPropertyFromFilter(searchItemRequest)
        );
    }

    private String getSortByPropertyFromFilter(SearchItemRequest searchItemRequest) {
        switch (searchItemRequest.getSortBy()) {
            case DEFAULT_SORT:
                return "name";
            case NEWNESS_SORT:
                return "start_time";
            case TIME_LEFT_SORT:
                return "end_time";
            case PRICE_SORT:
                return "start_price";
            default:
                throw new IllegalArgumentException("Value " + searchItemRequest.getSortBy().toString() + "is not part of SortCriterion enum!");
        }
    }
}
