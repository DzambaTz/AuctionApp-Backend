package com.example.AuctionApp.controllers;

import com.example.AuctionApp.models.User;
import com.example.AuctionApp.payload.request.UpdatePersonalDetailsRequest;
import com.example.AuctionApp.payload.response.MessageResponse;
import com.example.AuctionApp.security.services.implementations.UserDetailsImpl;
import com.example.AuctionApp.security.services.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserDataService userDataService;

    @GetMapping("/personal-info")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<?> getPersonalDetails(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<User> user = userDataService.getPersonalDetails(userDetails.getId());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("No user found!"));
        } else {
            return ResponseEntity.ok(user.get());
        }
    }

    @PutMapping("/personal-info")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<MessageResponse> updatePersonalDetails(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UpdatePersonalDetailsRequest personalDetails) {
        userDataService.updatePersonalDetails(userDetails.getId(), personalDetails);
        System.out.println(personalDetails);
        return ResponseEntity.ok(new MessageResponse("Personal info successfully updated!"));
    }

    @PutMapping("/deactivate")
    public ResponseEntity<MessageResponse> deactivateUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        userDataService.deactivateUser(userDetails.getId());
        return ResponseEntity.ok(new MessageResponse("User successfully deactivated"));
    }
}
