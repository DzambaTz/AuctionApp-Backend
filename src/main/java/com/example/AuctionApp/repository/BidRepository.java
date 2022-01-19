/**
 * BidRepository is a JPA repository used to query the bids database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.Bid;
import com.example.AuctionApp.payload.response.UserBidsItemResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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

    @Query(
            value = "SELECT bids.item_id as itemId, MAX(amount) as bidAmount, images[1] as imageUrl,name,cast((end_time - now()) as varchar)AS timeLeft,\n" +
                    "COUNT(DISTINCT bids.id) as count, highest_bid as maxBidAmount\n" +
                    "FROM bids\n" +
                    "LEFT JOIN items ON bids.item_id = items.id LEFT JOIN (SELECT item_id , MAX(amount) as highest_bid FROM bids GROUP BY item_id) maxBid ON maxBid.item_id = bids.item_id\n" +
                    "WHERE bids.user_id = ?1\n" +
                    "GROUP BY bids.item_id, start_price, images, name, end_time, highest_bid",
            nativeQuery = true
    )
    List<UserBidsItemResponse> getUserBids(Long userId);

    default void placeBid(Bid bid) {
        save(bid);
    }
}
