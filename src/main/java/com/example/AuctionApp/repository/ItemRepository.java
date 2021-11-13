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
}
