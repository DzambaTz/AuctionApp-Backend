/**
 * TestController is a class that was used during development to test user role functionality.
 *
 * @author Tarik Dzambic
 */

package com.example.AuctionApp.controllers;

import com.example.AuctionApp.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/seller")
    @PreAuthorize("hasRole('SELLER')")
    public String userAccess() {
        return "Seller Content.";
    }

    @GetMapping("/status")
    public ResponseEntity<MessageResponse> checkBackendStatus(){
        return ResponseEntity.ok(new MessageResponse("Backend is online!"));
    }
}

