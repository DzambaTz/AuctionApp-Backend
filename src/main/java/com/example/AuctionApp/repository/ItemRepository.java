/**
 * ItemRepository is a JPA repository used to query the items database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.payload.request.SearchItemRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderByStartTimeDesc();
    List<Item> findAllByOrderByEndTimeAsc();

    @Query(
            value = "SELECT id,category,description,end_time,name,start_price,start_time,subcategory,images, null as user_id FROM items " +
                    "WHERE start_price >= :#{#searchItemRequest.minPrice} AND start_price <= :#{#searchItemRequest.maxPrice} " +
                    "AND LOWER(name) LIKE concat('%',LOWER(:#{#searchItemRequest.search} ),'%') AND (category IN :#{#searchItemRequest.category} " +
                    "OR concat_ws('/',category ,subcategory) IN :#{#searchItemRequest.subcategory} )",
            nativeQuery = true
    )
    List<Item> getFilteredItems(SearchItemRequest searchItemRequest, Pageable p);

    @Query(
            value = "SELECT DISTINCT category FROM items",
            nativeQuery = true
    )
    List<String> getListOfCategories();

    @Query(
            value = "SELECT DISTINCT concat_ws('/',category,subcategory) FROM items",
    nativeQuery = true
    )
    List<String> getListOfSubcategories();

    @Query(
            value = "SELECT MIN(start_price) FROM items",
            nativeQuery = true
    )
    Float getMinPrice();

    @Query(
            value = "SELECT MAX(start_price) FROM items",
            nativeQuery = true
    )
    Float getMaxPrice();

    @Query(
            value = "SELECT items.id, images[1],name,cast((end_time - now()) as varchar) AS time_left,start_price, COUNT(DISTINCT bids.id)\n" +
                    "AS number_of_bids, COALESCE(MAX(DISTINCT bids.amount),0) AS max_bid FROM items LEFT OUTER JOIN bids " +
                    "ON items.id = bids.item_id WHERE end_time > now() AND items.user_id = ?1 GROUP BY items.id",
            nativeQuery = true
    )
    List<Tuple> getActiveUserItems(Long userId);

    @Query(
            value = "SELECT items.id, images[1],name,NULL AS time_left,start_price, COUNT(DISTINCT bids.id)\n" +
                    "AS number_of_bids, COALESCE(MAX(DISTINCT bids.amount),0) AS max_bid FROM items LEFT OUTER JOIN bids " +
                    "ON items.id = bids.item_id WHERE end_time < now() AND items.user_id = ?1 GROUP BY items.id",
            nativeQuery = true
    )
    List<Tuple> getSoldUserItems(Long userId);
}
