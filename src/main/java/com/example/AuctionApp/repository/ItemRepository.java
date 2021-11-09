package com.example.AuctionApp.repository;

import com.example.AuctionApp.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
