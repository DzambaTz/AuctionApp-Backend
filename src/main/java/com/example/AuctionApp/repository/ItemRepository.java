/**
 * ItemRepository is a JPA repository used to query the items database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.Item;
import com.example.AuctionApp.payload.request.SearchItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderByStartTimeDesc();
    List<Item> findAllByOrderByStartTimeAsc();

    @Query(
            value = "SELECT id,category,description,end_time,name,start_price,start_time,subcategory,images, null as user_id FROM items " +
                    "WHERE start_price >= :#{#searchItemRequest.minPrice} AND start_price <= :#{#searchItemRequest.maxPrice} " +
                    "AND LOWER(name) LIKE concat('%',LOWER(:#{#searchItemRequest.search} ),'%') AND (category IN :#{#searchItemRequest.category} " +
                    "OR concat_ws('/',category ,subcategory) IN :#{#searchItemRequest.subcategory} )",
            nativeQuery = true
    )
    List<Item> getFilteredItems(SearchItemRequest searchItemRequest);

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

}
