/**
 * BidRepository is a JPA repository used to query the bids database table.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
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
            value = "SELECT bids.item_id as id, MAX(amount) as bid_amount, images[1] as image_url,name,cast((end_time - now()) as varchar)AS time_left,\n" +
                    "COUNT(DISTINCT bids.id), highest_bid\n" +
                    "FROM bids\n" +
                    "LEFT JOIN items ON bids.item_id = items.id LEFT JOIN (SELECT item_id , MAX(amount) as highest_bid FROM bids GROUP BY item_id) maxBid ON maxBid.item_id = bids.item_id\n" +
                    "WHERE bids.user_id = ?1\n" +
                    "GROUP BY bids.item_id, start_price, images, name, end_time, highest_bid",
            nativeQuery = true
    )
    List<Tuple> getUserBids(Long userId);
}
