package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BidRepository extends JpaRepository<Bid, Long> {
    @Query(
            value = "SELECT coalesce ((SELECT max(amount) FROM bids WHERE item_id = ?1),0)",
            nativeQuery = true
    )
    Float findLargestBid(Long itemId);

    @Query(
            value = "SELECT COUNT(id) FROM bids WHERE item_id = ?1",
            nativeQuery = true
    )
    Integer countBids(Long itemId);
}
