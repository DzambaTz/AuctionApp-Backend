package com.example.AuctionApp.payload.request;

import com.example.AuctionApp.models.Direction;
import com.example.AuctionApp.models.SortCriterion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchItemRequest {
    List<String> category;
    List<String> subcategory;
    Float minPrice;
    Float maxPrice;
    String search;
    SortCriterion sortBy;
    Direction direction;
    Integer pageNumber;
    Integer pageSize;
}
