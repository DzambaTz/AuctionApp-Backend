package com.example.AuctionApp.controllers;

import com.example.AuctionApp.payload.request.PersonalInfoChangeRequest;
import com.example.AuctionApp.payload.request.PhotoChangeRequest;
import com.example.AuctionApp.security.services.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserDataService userDataService;

    @GetMapping("/profilePhoto/{userId}")
    public ResponseEntity<?> getProfilePhoto(@PathVariable("userId") Long userId){
        return userDataService.getProfilePhoto(userId);
    }

    @PutMapping("/profilePhoto/{userId}")
    public ResponseEntity<?> changeProfilePhoto(@PathVariable("userId") Long userId, @RequestBody PhotoChangeRequest body){
        return userDataService.changeProfilePhoto(userId, body);
    }

    @GetMapping("/personalInfo/{userId}")
    public ResponseEntity<?> getPersonalInfo(@PathVariable("userId") Long userId){
        return userDataService.getPersonalInfo(userId);
    }

    @PutMapping("/personalInfo/{userId}")
    public ResponseEntity<?> changePersonalInfo(@PathVariable("userId") Long userId, @RequestBody PersonalInfoChangeRequest body){
        return userDataService.changePersonalInfo(userId, body);
    }

    @PutMapping(path = "/deactivate")
    public ResponseEntity<?> deactivateUser(@RequestHeader("Authorization") String jwt){
        return userDataService.deactivateUser(jwt);
    }
}
