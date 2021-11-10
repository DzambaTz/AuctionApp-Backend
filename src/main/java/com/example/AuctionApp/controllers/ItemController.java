package com.example.AuctionApp.controllers;

import com.example.AuctionApp.payload.request.BidRequest;
import com.example.AuctionApp.security.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping(path= "/{itemId}")
    public ResponseEntity<?> getItemData(@PathVariable("itemId") Long itemId) {
        return itemService.getItemData(itemId);
    }
}
