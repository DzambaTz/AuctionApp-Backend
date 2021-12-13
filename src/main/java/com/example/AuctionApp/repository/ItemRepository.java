/**
 * ItemRepository is a JPA repository used to query the items database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(
            value = "SELECT id,category,description,end_time,name,start_price,start_time,subcategory,images, null as user_id FROM items ORDER BY start_time DESC",
            nativeQuery = true
    )
    List<Item> getNewArrivals();

    @Query(
            value = "SELECT id,category,description,end_time,name,start_price,start_time,subcategory,images, null as user_id FROM items ORDER BY end_time ASC",
            nativeQuery = true
    )
    List<Item> getLastChanceItems();

    @Query(
            value = "SELECT id,category,description,end_time,name,start_price,start_time,subcategory,images, null as user_id FROM items " +
                    "WHERE start_price >= :minPrice AND start_price <= :maxPrice AND name LIKE concat('%',:search ,'%') AND (category IN :category OR concat_ws('/',category ,subcategory) IN :subcategory )",
            nativeQuery = true
    )
    List<Item> getFilteredItems(List<String> category, List<String> subcategory, Float minPrice, Float maxPrice, String search);

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
